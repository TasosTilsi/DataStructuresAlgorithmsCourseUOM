/******************************************************************************
 * Compilation: javac p5e3.java
 * Execution: java p5e3 < in-p5e3.txt > out-p5e3.txt
 * Dependencies: BoyerMoore.java StdIn.java StdOut.java
 * Data files: in-p5e3.txt
 *
 ******************************************************************************/

public class p5e3 {

    public static void main(String[] args) {

        String pattern = "rab";
        String text = "abacadabrabracabracadabrabrabracad";
        BoyerMoore bm;

        while (!StdIn.isEmpty()) {
            pattern = StdIn.readString();   //reads pattern
            text = StdIn.readString();      //reads txt
        }

        bm = new BoyerMoore(pattern);
        StdOut.println();
        bm.printAll(text);

    }

}
