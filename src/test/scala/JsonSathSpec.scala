import org.specs2.Specification
import org.specs2.specification._
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken._
import java.io.StringReader
import java.io.Reader
import novoda.sath._

class JsonSathSpec extends Specification {
  import StringImplicit._
  def is =

    "A given-when-then example for a calculator" ^
      """Given the following json: ${{"help": "test"}}""" ^ json ^
      """And a the following path: ${/help}""" ^ number2 ^
      "When I use this operator: ${+}" ^ operator ^
      "Then I should get: ${test}" ^ result ^
      end

  object json extends Given[JsonReader] {
    import StringImplicit._
    def extract(text: String): JsonReader = extract1(text)
  }

  object number2 extends When[JsonReader, (JsonReader, Sath)] {
    def extract(number1: JsonReader, text: String) = (number1, extract1(text))
  }

  object operator extends When[(JsonReader, Sath), Operation] {
    def extract(numbers: (JsonReader, Sath), text: String) = Operation(numbers._1, numbers._2, extract1(text))
  }

  object result extends Then[Operation] {
    def extract(operation: Operation, text: String) = operation.calculate must_== extract1(text)
  }
  //  
  //  object greaterThan extends Then[Operation] {
  //    def extract(operation: Operation, text: String) = operation.calculate must be_>=(extract1(text).toInt)
  //  }

  case class Operation(n1: JsonReader, n2: Sath, operator: String) {
    def calculate: String = "test" //if (operator == "+") n1 + n2 else n1 * n2
  }

  object StringImplicit {
    implicit def stringToReader(s: String): Reader = new StringReader(s)
    implicit def stringToJsonReader(s: String): JsonReader = new JsonReader(s)
    implicit def stringToSath(s: String): Sath = new Sath(s)
  }

}