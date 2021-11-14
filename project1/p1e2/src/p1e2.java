
public class p1e2 {

	public static void main(String[] args) {
		
		Stack<String> myStack = new Stack<>();
		
		while (!StdIn.isEmpty()) {
		      String value = StdIn.readString();
//		      StdOut.println(value);
		      myStack.push(value);
		}
		
		StdOut.println(myStack.firstThreeInsertedItemsDisplay());

	}

}
