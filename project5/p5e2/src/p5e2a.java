import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/******************************************************************************
 * Compilation: javac p5e2a.java
 * Execution: java p5e2a < ../resources/in-p5e2.txt > ../resources/out-p5e2a.txt
 * Dependencies: TST.java Queue.java StdIn.java StdOut.java
 * Data files: list.txt
 *
 ******************************************************************************/
public class p5e2a {

    public static void main(String[] args) {

        String path;
        if (getOperatingSystem().equalsIgnoreCase("linux")) {
            path = System.getProperty("user.dir") + "/resources/in-p5e2.txt";
        } else {
            path = System.getProperty("user.dir") + "\\resources\\in-p5e2.txt";
        }

        if (args.length != 0) {
            path = args[0]; //passed argument from command line execution if exists
        }

        readItemsFromFile(path);

    }

    private static void readItemsFromFile(String path) {
        TST<String> tst = new TST<>();
        ST<String, String> st = new ST<>();

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                line++;
                System.out.println(getLinesHidesTheWord(data,line,tst));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        StdOut.println("");
//        printData();
    }

    /*private static void printData() {
        StdOut.println(ts.toString());
        StdOut.println(String.format("Size = %d", ts.size()));
    }*/

    private static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }

    private static String getLinesHidesTheWord(String data,int line,TST<String> tst) {
        System.out.println(data);
        String[] words = data.split(" ");
        for (String word : words) {
            word = word.toLowerCase(Locale.ROOT).replace(",", "");
//            System.out.println(word);
            tst.put(word,String.valueOf(line));
        }

        for (String key : tst.keys()) {
            System.out.println(key+ ""+tst.get(key));
        }

        return "";
    }

}
