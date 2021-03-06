/******************************************************************************
 * Compilation: javac p5e1b.java
 * Execution: java p5e1b < ../resources/list.txt > ../resources/out-p5e1b.txt
 * Dependencies: ThreadStrings.java RedBlackBST.java Queue.java StdIn.java StdOut.java
 * Data files: list.txt
 *
 ******************************************************************************/
public class p5e1b {

    public static void main(String[] args) {

        ThreadStrings ts = new ThreadStrings();

        readItemsFromFile(ts);

    }

    private static void readItemsFromFile(ThreadStrings ts) {
        StdOut.println("Adding keys in Structure");
        StdOut.println("");
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();  //reads input from Standard Input
            printIfKeyExistsInStructure(value, ts);
            ts.add(value);//push strings into ts
        }
        StdOut.println("");
        printData(ts);
    }

    private static void printData(ThreadStrings ts) {
        StdOut.println(ts.toString());
        StdOut.println(String.format("Size = %d", ts.size()));
    }

    private static void printIfKeyExistsInStructure(String value, ThreadStrings ts){
        StdOut.println("Checking if Key contained in Structure: "+value + " " + ts.contains(value));
    }

}
