
public class p1e1 {

	public static void main(String[] args) {
		
		QueueCircular<String> myQueue = new QueueCircular<>();
		
		while (!StdIn.isEmpty()) {
		      String value = StdIn.readString();
//		      StdOut.println(value);
		      myQueue.enqueue(myQueue,value);
		}
		
		StdOut.println("queue size " + myQueue.size());
		
		StdOut.println(myQueue.displayDequeue(myQueue));
		
		StdOut.println("queue size " + myQueue.size());
		
	}

}
