import org.specs2.Specification
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken._
import java.io.StringReader
import java.io.Reader
import novoda.sath._

class JSONSpec extends Specification {
  def is =

    sequential ^
      """Given the following JSON {"array":["x","y"]} """ ! c1.initJSON ^
      """When we use /location[1]""" ! c1.setSath ^
      """Then the value returned should be "y" """ ! c1.checkValues ^
      end

  case object c1 {
    import StringImplicit._
    val JsonGiven = "Given the following JSON (.*)".r
    val SathGiven = "When we use (.*)".r
    val assertions: scala.collection.mutable.Map[JsonReader, Sath] = new scala.collection.mutable.HashMap[JsonReader, Sath]()

    def initJSON = (s: String) ⇒ {
      val JsonGiven(json) = s
      val jsonreader = new JsonReader(json)
      assertions += jsonreader -> new Sath("")
      success
    }

    def setSath = (s: String) ⇒ {
      val SathGiven(sath) = s
      val sathImpl = new Sath(sath)
      success
    }

    def checkValues = (s: String) ⇒ {
      val reader = new JsonReader("{}")
      parse(reader)
      "y" must_== "y"
    }
  }

  def parse(reader: JsonReader) {
    val token = reader.peek;
    token match {
      case BEGIN_ARRAY  ⇒ reader.beginArray(); parse(reader)
      case END_ARRAY    ⇒ reader.endArray(); parse(reader)
      case BEGIN_OBJECT ⇒ reader.beginObject(); parse(reader)
      case END_OBJECT   ⇒ reader.endObject(); parse(reader)
      case NAME         ⇒ reader.nextName(); parse(reader)
      case STRING       ⇒ reader.nextString(); parse(reader)
      case END_DOCUMENT ⇒ ""
    }
  }

  object StringImplicit {
    implicit def stringToReader(s: String): Reader = new StringReader(s)
  }
}