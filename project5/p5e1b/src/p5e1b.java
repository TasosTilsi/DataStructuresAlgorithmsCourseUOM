/******************************************************************************
 * Compilation: javac p5e1a.java
 * Execution: java p5e1a < ../resources/list.txt > ../resources/out-p5e1a.txt
 * Dependencies: ThreadStrings.java StdIn.java StdOut.java
 * Data files: list.txt
 *
 ******************************************************************************/
public class p5e1b {

    public static void main(String[] args) {

        ThreadStrings ts = new ThreadStrings();

        readItemsFromFile(ts);

    }

    private static void readItemsFromFile(ThreadStrings ts) {

        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();  //reads input from Standard Input
            StdOut.println(value + " " + ts.contains(value));
            ts.add(value);//push strings into ts
        }
        printData(ts);
    }

    private static void printData(ThreadStrings ts) {
        StdOut.println(ts.toString());
        StdOut.println(String.format("Size = %d", ts.size()));
    }

}
