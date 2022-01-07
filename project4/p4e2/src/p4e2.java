/******************************************************************************
 * Compilation: javac p4e2.java
 * Execution: java p4e2 < ../resources/tinyTale.txt > ../resources/out-p4e2_tinyTale.txt
 * Execution: java p4e2 < ../resources/mobydick.txt > ../resources/out-p4e2_mobydick.txt
 * Dependencies: UniQueue.java SET.java Queue.java StdIn.java StdOut.java
 * Data files: ../resources/mobydick.txt ../resources/tinyTale.txt
 ******************************************************************************/

public class p4e2 {

    public static void main(String[] args) {
        UniQueue<String> myq = new UniQueue<>();

        readItems(myq);

    }

    private static void readItems(UniQueue myQueue) {
        int count = 0;
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();  //reads input from Standard Input
            myQueue.enqueue(value);                //push strings into queue
            count++;
        }
        printData(myQueue, count);
    }

    private static void printData(UniQueue myQueue, int count) {
        StdOut.println(String.format("Size of input file = %d, Unique size= %d", count, myQueue.size()));
        if (count <= 60){
            StdOut.println(myQueue.toString());
        }
    }

}
