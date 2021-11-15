/******************************************************************************
* Compilation: javac p1e1.java
* Execution: java p1ex1 < in-p1e1.txt > out-p1e1.txt
* Dependencies: StdIn.java StdOut.java QueueCircular.java
* Data files: in-p1e1.txt
*
******************************************************************************/

public class p1e1 {

	public static void main(String[] args) {
		
		QueueCircular<String> myQueue = new QueueCircular<>(); //initializes a new QueueCircular object containing Strings
	
		
		while (!StdIn.isEmpty()) {
		      String value = StdIn.readString(); //reads input from Standard Input
//		      StdOut.println(value);
		      myQueue.enqueue(myQueue,value); //inserts strings into queue
		}
		
		StdOut.println("queue size " + myQueue.size()); //prints the size of the queue
		
		StdOut.println(myQueue.displayDequeue(myQueue)); //prints out the queue while removes items 1-1 from it
		
		StdOut.println("queue size " + myQueue.size()); //prints the size of the queue
		
	}

}
