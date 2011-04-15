package novoda.sath;

public interface SathVisitor {

	void start();

	void visitStartElement(String elementName);

	void visitEndElement();

	void visitStartArray(int index);

	void visitEndArray();

	void end();

//	void addCallback(Callback callback);
//
//	public interface Callback {
//		public void onHit(SathVisitor visitor);
//	}
}