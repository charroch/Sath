package novoda.sath;

import java.util.Stack;

public class Sath implements SathVisitor {

	private final String sath;

	private Stack<String> pathStack;

//	Callback callback;

	private boolean inVisitState = false;

	public Sath(String sath) {
		this.sath = sath;
	}

	@Override
	public void visitStartElement(String elementName) {
		inVisitState = (elementName.equalsIgnoreCase(pathStack.pop()));
	}

	@Override
	public void visitEndElement() {
		inVisitState = true;
	}

	@Override
	public void visitStartArray(int index) {
		String current = pathStack.peek();
		if (current.charAt(0) == '[' && current.charAt(1) == '#') {
		}
	}

	@Override
	public void visitEndArray() {
	}

	@Override
	public void start() {
		pathStack = new Stack<String>();
		for (String path : sath.split("/")) {
			pathStack.push(path);
		}
	}

	@Override
	public void end() {
		pathStack.clear();
	}

//	@Override
//	public void addCallback(Callback callback) {
//		this.callback = callback;
//	}

	@Override
	public String toString() {
		return "" + pathStack;
	}
}
