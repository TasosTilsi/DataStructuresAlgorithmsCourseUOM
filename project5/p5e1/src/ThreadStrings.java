import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadStrings {


    private HashMap<String, String> set;
    private String previousKey;

    ThreadStrings() {
        this.set = new LinkedHashMap<>();
        previousKey=null;
    }

    /**
     * Adds the given parameter in the set
     *
     * @param s given String
     */
    public void add(String s) {
        if(set.isEmpty() || !contains(s)) {
            set.put(s, previousKey);
            previousKey=s;
        }
    }

    /**
     * Returns a boolean if the given parameter is in the set
     *
     * @param s given String
     */
    public boolean contains(String s) {
        return set.containsKey(s);
    }

    /**
     * Return the previous string that was inserted before the given parameter
     *
     * @param s given String
     */
    public String previousKey(String s) {
//        String previousKey = null;
//
//        for (Map.Entry<String, String> entry : set.entrySet()) {
//            if (entry.getKey().equals(s)) {
//                break;
//            }
//            previousKey = entry.getKey();
//        }

        return set.get(s);
    }

    public int size(){
        return set.size();
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String, String> entry : set.entrySet()) {
            s.append(entry.getKey() + " - " + entry.getValue());
            s.append("\n");
        }
        return s.toString();
    }

    /**
     * demo code
     */
    public static void main(String[] args) {
        ThreadStrings ts = new ThreadStrings();
        ts.add("dog"); // [ "dog" ]
        ts.add("cat"); // [ "dog", "cat" ]
        ts.add("bear"); // ["dog", "cat, "bear"]
        ts.add("bear"); // ["dog", "cat, "bear"]  // (η προσθήκη διπλότυπου δεν έχει κάποια επίδραση στο νήμα)
        System.out.println("-----------------------------------------------------------------------");
        StdOut.println(ts.toString());
        StdOut.println(ts.size());
        StdOut.println(ts.contains("dog")); // true
        StdOut.println(ts.contains("tiger")); // false
        StdOut.println(ts.previousKey("cat"));// "dog"
        StdOut.println(ts.previousKey("bear"));// "cat"
        StdOut.println(ts.previousKey("dog"));// null
    }

}