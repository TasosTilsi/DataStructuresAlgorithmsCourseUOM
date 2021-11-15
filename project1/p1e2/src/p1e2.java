/******************************************************************************
* Compilation: javac p1e2.java
* Execution: java p1ex2 < in-p1e2.txt > out-p1e2.txt
* Dependencies: StdIn.java StdOut.java Stack.java
* Data files: in-p1e2.txt
*
******************************************************************************/

public class p1e2 {

	public static void main(String[] args) {
		
		Stack<String> myStack = new Stack<>(); //initializes a new Stack object containing Strings
		
		while (!StdIn.isEmpty()) {
		      String value = StdIn.readString();  //reads input from Standard Input
//		      StdOut.println(value);
		      myStack.push(value); //push strings into stack
		}
		
		StdOut.println(myStack.firstThreeInsertedItemsDisplay());  //prints out the requested 3 first inserted strings 

	}

}
