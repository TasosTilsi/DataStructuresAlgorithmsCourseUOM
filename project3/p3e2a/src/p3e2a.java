/******************************************************************************
 * Compilation: javac p3e2a.java
 * Execution:
 java p3e2a 3 < ../resources/in-p3e2a.txt > ../resources/out-p3e2a.txt
 * Dependencies: StdIn.java StdOut.java Stack.java StackWithMinMax.java
 * Data files: in-p3e2a.txt
 ******************************************************************************/

import java.util.Arrays;
import java.util.Collections;

public class p3e2a {

    public static void main(String[] args) {
        int k = 3;
        if (args.length != 0) {
            k = Integer.parseInt(args[0]); //passed argument from command line execution if exists
        }

        Integer[] array1 = StdArrayIO.readInteger1D(); //read first array of integers
        Integer[] array2 = StdArrayIO.readInteger1D(); //read second array of integers

        printArray(array1, 0); //print array1
        printArray(array2, 0); //print array2

        Arrays.sort(array1, Collections.reverseOrder());    //sort array1
        Arrays.sort(array2, Collections.reverseOrder());    //sort array2

        printArray(array1, 0);//print sorted array1
        printArray(array2, 0);//print sorted array2

        KMaxSum.findMaxSum(array1, array2, k);

    }

    private static void printArray(Integer[] array, int i) {
        if (i >= array.length) {
            System.out.println("");
            return;
        }
        System.out.print(array[i] + " ");
        printArray(array, i + 1);
    }

}
