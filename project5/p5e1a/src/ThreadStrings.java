public class ThreadStrings {

    private SeparateChainingHashST<String, String> set;
    private int size = 0;
    private String previousKey;

    ThreadStrings() {
        this.set = new SeparateChainingHashST<>(1);
        previousKey = "null";
    }

    /**
     * Adds the given parameter in the set
     *
     * @param s given String
     */
    public void add(String s) {
        if (set.isEmpty() || !contains(s)) {
            set.put(s, previousKey);
            size++;
            previousKey = s;
        }
    }

    /**
     * Returns a boolean if the given parameter is in the set
     *
     * @param s given String
     */
    public boolean contains(String s) {
        for (String key : set.keys()) {
            if (key.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the previous string that was inserted before the given parameter
     *
     * @param s given String
     */
    public String previousKey(String s) {
        return set.get(s);
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String key : set.keys()) {
            s.append(key + " - " + set.get(key));
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
        ts.add("bear"); // ["dog", "cat, "bear"]
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