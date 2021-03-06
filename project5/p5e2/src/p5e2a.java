import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/******************************************************************************
 * Compilation: javac p5e2a.java
 * Execution: java p5e2a ../resources/in-p5e2.txt > ../resources/out-p5e2a.txt
 * Dependencies: TST.java Queue.java StdIn.java StdOut.java
 * Data files: in-p5e2.txt
 *
 ******************************************************************************/
public class p5e2a {

    private final static boolean DEBUG_MODE = false;

    public static void main(String[] args) {

        String path;
        if (!isOpeatingSystemWindows()) {
            path = getProjectPath() + "/resources/in-p5e2.txt";
        } else {
            path = getProjectPath() + "\\resources\\in-p5e2.txt";
        }

        if (args.length != 0) {
            path = args[0]; //passed argument from command line execution if exists
        }

        if (DEBUG_MODE) System.out.println(path);

        TST<String> tst = new TST<>();

        readItemsFromFile(path, tst);

        printData(path, tst);

    }

    /**
     * print data
     *
     * @param path
     * @param tst
     */
    private static void printData(String path, TST<String> tst) {
        StdOut.println("Index of File: " + Paths.get(path).getFileName());
        for (String key : tst.keys()) {
            StdOut.println(key + " " + tst.get(key));
        }
    }

    /**
     * read each line from the given file
     *
     * @param path
     * @param tst
     */
    private static void readItemsFromFile(String path, TST<String> tst) {

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            int line = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().toLowerCase(Locale.ROOT).replaceAll("[~,.!'`;\":/#$%^&*()-_+={}?<>]", "");
                line++;
                if (data.isEmpty()) continue;
                getLinesHidesTheWord(data, line, tst);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Put words in data structure and append each line exists
     *
     * @param data
     * @param line
     * @param tst
     */
    private static void getLinesHidesTheWord(String data, int line, TST<String> tst) {
        if (DEBUG_MODE) System.out.println(data);
        String[] words = data.split("\\s+");
        if (DEBUG_MODE) System.out.println(Arrays.toString(words));
        for (String word : words) {

            if (word.isEmpty()) continue;

            if (tst.contains(word)) {
                tst.put(word, tst.get(word) + "-" + line);
            } else {
                tst.put(word, String.valueOf(line));
            }
        }

    }

    /**
     * Check in what operating system the program runs
     *
     * @return os operating system's name
     */
    private static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        if (DEBUG_MODE) System.out.println("Using System Property: " + os);
        return os;
    }

    private static boolean isOpeatingSystemWindows() {
        return getOperatingSystem().equalsIgnoreCase("windows");
    }

    /**
     * Get Project Path for given file
     *
     * @return project path
     */
    private static String getProjectPath() {
        return System.getProperty("user.dir");
    }

}
