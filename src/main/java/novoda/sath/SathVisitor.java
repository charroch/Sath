package novoda.sath;

public interface SathVisitor {

	void start();

	void visitStartElement(String elementName);

	void visitEndElement();

	void visitStartArray(int index);

	void visitEndArray();

	void end();

	public interface Callback<T> {
		public void onHit(SathVisitor visitor, T reader);
	}
}