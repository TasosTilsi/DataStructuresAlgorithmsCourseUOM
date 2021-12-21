import java.util.ArrayList;
import java.util.Comparator;

public class KMaxSum {

    public static void findMaxSum(Integer[] ar1, Integer[] ar2, int k) {
        // max heap.
        MaxPQ<Integer> pq = new MaxPQ<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        ArrayList<Node> keysInPq = new ArrayList<>();
        // Insert all the possible
        // combinations in max heap.
        for (int i = 0; i < ar1.length; i++) {
            for (int j = 0; j < ar2.length; j++) {
                Integer sum = ar1[i] + ar2[j];
                Node node = new Node(i, j, sum);
                keysInPq.add(node);
                pq.insert(node.sum);
                keysInPq.sort(Node::compareTo);
                printArrayKeysInPq(keysInPq);
            }
        }

        // Traverse the sorted array
        for (int i = 0; i < keysInPq.size(); i++) {
            // Move the index ahead while
            // there are duplicates
            while (i < keysInPq.size() - 1 && keysInPq.get(i).sum == keysInPq.get(i + 1).sum) {
                i++;
            }
            // print last occurrence of
            // the current element
            if (k > 0) {
                System.out.print(keysInPq.get(i).sum + " ");
                System.out.println(String.format("sum=%d, a[%d]=%d, b[%d]=%d", keysInPq.get(i).sum, keysInPq.get(i).nums1Index, ar1[keysInPq.get(i).nums1Index], keysInPq.get(i).nums2Index, ar2[keysInPq.get(i).nums2Index]));
                System.out.print("k=" + k + " ");
                printArrayKeysInPq(keysInPq, keysInPq.get(i).sum);
                k--;
            }
            pq.delMax();
        }
    }

    private static void printArrayKeysInPq(ArrayList<Node> ar) {
//        ar.sort(Node::compareTo);
        System.out.print("keys in pq:");
        for (Node child : ar) {
            System.out.print("[" + child.nums1Index + ", " + child.nums2Index + ", " + child.sum + "]");
        }
        System.out.println("");
    }

    private static void printArrayKeysInPq(ArrayList<Node> ar, Integer sum) {
//        ar.sort(Node::compareTo);
        System.out.print("keys in pq:");
        for (Node child : ar) {
            if (child.sum <= sum) {
                System.out.print("[" + child.nums1Index + ", " + child.nums2Index + ", " + child.sum + "]");
            }
        }
        System.out.println("");
    }


    static class Node implements Comparable<Node> {
        public int nums1Index;
        public int nums2Index;
        public int sum;

        public Node(int nums1Index, int nums2Index, int sum) {
            super();
            this.nums1Index = nums1Index;
            this.nums2Index = nums2Index;
            this.sum = sum;
        }

        @Override
        public int compareTo(Node node) {
            // TODO Auto-generated method stub
            /*if(node.sum-this.sum==0){
                return node.nums1Index-this.nums1Index;
            }else*/
            return node.sum - this.sum;
        }
    }


    // Driver Code
    public static void main(String[] args) {
        Integer A[] = {4, 2, 5, 1};
        Integer B[] = {8, 0, 5, 3};
        int K = 3;

        // Function Call
        findMaxSum(A, B, K);


    }
}
