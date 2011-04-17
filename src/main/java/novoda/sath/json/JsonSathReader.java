package novoda.sath.json;

import java.io.IOException;

import novoda.sath.Sath;
import novoda.sath.SathVisitor;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class JsonSathReader {

	private Sath sath;

	public void registerCallback(SathVisitor.Callback<JsonReader> callback) {
	}

	void prettyprint(JsonReader reader) throws IOException {
		while (true) {
			JsonToken token = reader.peek();
			switch (token) {
			case BEGIN_ARRAY:
				reader.beginArray();
				sath.visitStartArray(0);
				break;
			case END_ARRAY:
				reader.endArray();
				sath.visitEndArray();
				break;
			case BEGIN_OBJECT:
				reader.beginObject();
				// sath.visit
				break;
			case END_OBJECT:
				reader.endObject();
				break;
			case NAME:
				String name = reader.nextName();
				break;
			case STRING:
				String s = reader.nextString();
				break;
			case NUMBER:
				String n = reader.nextString();
				break;
			case BOOLEAN:
				boolean b = reader.nextBoolean();
				break;
			case NULL:
				reader.nextNull();
				break;
			case END_DOCUMENT:
				return;
			}
		}
	}
}
