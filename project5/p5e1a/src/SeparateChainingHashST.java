/******************************************************************************
 *  Compilation:  javac SeparateChainingHashST.java
 *  Execution:    java SeparateChainingHashST < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/34hash/tinyST.txt
 *
 *  A symbol table implemented with a separate-chaining hash table.
 *
 ******************************************************************************/

/**
 * The {@code SeparateChainingHashST} class represents a symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}—setting the
 * value associated with a key to {@code null} is equivalent to deleting the key
 * from the symbol table.
 * <p>
 * This implementation uses a separate chaining hash table. It requires that
 * the key type overrides the {@code equals()} and {@code hashCode()} methods.
 * The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 * operation is constant, subject to the uniform hashing assumption.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * For other implementations, see {@link //ST}, {@link //BinarySearchST},
 * {@link SequentialSearchST}, {@link //BST}, {@link //RedBlackBST}, and
 * {@link //LinearProbingHashST},
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;                                // number of key-value pairs
    private int m;                                // hash table size
    private SequentialSearchST<Key, Value>[] st;  // array of linked-list symbol tables


    /**
     * Initializes an empty symbol table.
     */
    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     *
     * @param m the initial number of chains
     */
    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<Key, Value>();
    }

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    // hash function for keys - returns value between 0 and m-1
    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    // hash function for keys - returns value between 0 and m-1 (assumes m is a power of 2)
    // (from Java 7 implementation, protects against poor quality hashCode() implementations)
    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

//-------------------------------------------------------------------------------------------------------------------//

    /**
     * Returns the size of this symbol table.
     *
     * @return the size of this symbol table
     */
    public int size() {
        return m;
    }

    /**
     * Returns the number of key value pairs of this symbol table
     *
     * @return the number of key value pairs symbol table
     */
    public int getNumberOfKeyValuePairs() {
        return n;
    }

    /**
     * Returns the max chain size for this symbol table
     *
     * @return the max chain size for this symbol table
     */
    public int getMaxChainSize() {
        return getChainCounts().max();
    }

    /**
     * Return the keys from the chain that has the max size in this symbol table in string
     * @return the keys from the chain that has the max size in this symbol table in string
     */
    public String showMaxChainKeys(){
        String maxChainKeys = "";
        for (int i = 0; i < size(); i++) {
            if (st[i].size() == getMaxChainSize()) {
                maxChainKeys = st[i].keys().toString();
                break;
            }
        }
        return maxChainKeys;
    }

    /**
     * Returns the min chain size for this symbol table
     *
     * @return the min chain size for this symbol table
     */
    public int getMinChainSize() {
        return getChainCounts().min();
    }

    /**
     * Return the keys from the chain that has the min size in this symbol table in string
     * @return the keys from the chain that has the min size in this symbol table in string
     */
    public String showMinChainKeys() {
        String minChainKeys = "";
        for (int i = 0; i < size(); i++) {
            if (st[i].size() == getMinChainSize()) {
                minChainKeys = st[i].keys().toString();
                break;
            }
        }
        return minChainKeys;
    }

    /**
     * Returns an array that contains a count for each chain in the hash table
     *
     * @return an array that contains a count for each chain in the hash table
     */
    private SET<Integer> getChainCounts() {
        SET<Integer> countSet = new SET<>();
        int count = 0;
        for (int i = 0; i < size(); i++) {
            count = st[i].size();
            if (count != 0) {
                countSet.add(count);
            }
        }
        return countSet;
    }

//-------------------------------------------------------------------------------------------------------------------//

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key};
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param key the key
     * @return the value associated with {@code key} in the symbol table;
     * {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10 * m) resize(2 * m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, val);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }

    // return keys in symbol table as an Iterable
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                queue.enqueue(key);
        }
        return queue;
    }


    /**
     * Unit tests the {@code SeparateChainingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Integer> st = new SeparateChainingHashST<Integer, Integer>();
        for (int i = 0; i < 40; i++) {
//            Integer key = StdRandom.uniform(45);
            st.put(i, i+1);
        }
//
        // print keys
        for (Integer s : st.keys())
            StdOut.println(s + " " + st.get(s));

        st.getMaxChainSize();

    }

}
