/******************************************************************************
 * Compilation: javac p4e3.java
 * Execution: java p4e3 3 > ../resources/out-p4e3_1.txt
 * Execution: java p4e3 4 > ../resources/out-p4e3_2.txt
 * Execution: java p4e3 5 > ../resources/out-p4e3_3.txt
 * Execution: java p4e3 6 > ../resources/out-p4e3_4.txt
 * Dependencies: Queue.java SeparateChainingHashST.java SequentialSearchST.java StdRandom.java StdIn.java StdOut.java
 * Data files: none
 ******************************************************************************/

public class p4e3 {

    public static void main(String[] args) {

        int N = (int) Math.pow(10,3);
        if (args.length != 0) {
            N = (int) Math.pow(10,Integer.parseInt(args[0])); //passed argument from command line execution if exists
        }
        SeparateChainingHashST<Integer,Integer> hashST;

        hashST = generateRandomNumbers(N);
        printData(N,hashST);

    }

    private static SeparateChainingHashST<Integer,Integer> generateRandomNumbers(int N){
        SeparateChainingHashST<Integer,Integer> hashST = new SeparateChainingHashST<>(N/100);
        for (int i = 0; i < N; i++) {
            int value = StdRandom.uniform(N);
            hashST.put(value,i);
        }
        return hashST;
    }

    private static void printData(int N, SeparateChainingHashST<Integer,Integer> hashST){
        StdOut.println("Hash table size = " + hashST.size());
        StdOut.println("N = " + N + ", Number of Key-value pairs = "+ hashST.getNumberOfKeyValuePairs()+ ", max chain size = " +hashST.getMaxChainSize() +", min chain size = " + hashST.getMinChainSize());
        StdOut.println("----MinChain----");
        StdOut.println(hashST.showMinChainKeys());
        StdOut.println("----MaxChain----");
        StdOut.println(hashST.showMaxChainKeys());
    }

}
