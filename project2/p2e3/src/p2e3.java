/******************************************************************************
 * Compilation: javac p2e3.java
 * Execution:
 java p2e3 < in-p2e3.txt > out-p2e3.txt
 * Dependencies: StdIn.java StdOut.java ......
 * Data files:
 in-p2e3.txt
 *
 ******************************************************************************/
import java.util.Arrays;

public class p2e3 {
    public static void main(String[] args) {
        int x = 14;
        if (args.length!=0){
            x = Integer.parseInt(args[0]); //passed argument from command line execution if exists
        }

        int[] array1 = StdArrayIO.readInt1D();
        int[] array2 = StdArrayIO.readInt1D();
        StdOut.println("array1: size = ");
        StdArrayIO.print(array1);
        StdOut.println("array2: size = ");
        StdArrayIO.print(array2);

        QuickX.sort(array1);
        QuickX.sort(array2);

        StdOut.println("Asked Number to find the closest pair: x = "+ x);
        findClosest(array1,array2,array1.length,array2.length,x);

    }

    public static void findClosest(int ar1[], int ar2[], int ar1Size, int ar2Size, int x) {
        // Initialize the diff between pair sum and x.
        int diff = Integer.MAX_VALUE;

        // ar1Result and ar2Result are result indexes from ar1[] and ar2[]
        int ar1Result = 0;
        int ar2Result = 0;

        // Start from left side of ar1[] and right side of ar2[]
        int left = 0;
        int right = ar2Size - 1;

        while (left < ar1Size && right >= 0) {

            // If this pair is closer to x than the previously found closest
            // then update ar1Result, ar2Result and diff
            if (Math.abs(ar1[left] + ar2[right] - x) < diff) {
                ar1Result = left;
                ar2Result = right;
                diff = Math.abs(ar1[left] + ar2[right] - x);
            }

            // If sum of this pair is more than x, move to smaller
            // side
            if (ar1[left] + ar2[right] > x)
                right--;
            else  // move to the greater side
                left++;
        }

        // Print the result
        StdOut.println("The closest pair is [" + ar1[ar1Result] + ", " + ar2[ar2Result] + "]");
    }
}
