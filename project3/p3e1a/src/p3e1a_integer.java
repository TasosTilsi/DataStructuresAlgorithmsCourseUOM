/******************************************************************************
 * Compilation: javac p3e1a_integer.java
 * Execution:
    java p3e1a_integer < ../resources/in-p3e1a-integer.txt > ../resources/out-p3e1a-integer.txt
 * Dependencies: StdIn.java StdOut.java Stack.java StackWithMinMax.java
 * Data files: in-p3e1a-integer.txt
 ******************************************************************************/

public class p3e1a_integer {

    public static void main(String[] args) {
        StackWithMinMax myStack = new StackWithMinMax<Integer>();

        readItems(myStack);

        removeItems(myStack);
    }

    private static void readItems(StackWithMinMax myStack) {
        while (!StdIn.isEmpty()) {
            int value = StdIn.readInt();  //reads input from Standard Input
            myStack.push(value);                //push strings into stack
            StdOut.println(String.format("Top Element: %d min: %d max: %d size = %d", value, myStack.min(), myStack.max(), myStack.size()));
        }
    }

    private static void removeItems(StackWithMinMax myStack) {
        StdOut.println(String.format("Popping each element of the stack"));
        while(myStack.size()>0){
            int max = (int) myStack.max();
            int min = (int) myStack.min();
            int size = myStack.size();
            int poppedItem = (int) myStack.pop();
            StdOut.println(String.format("Top Element: %d min: %d max: %d size = %d", poppedItem, min, max, size));
            StdOut.println(String.format("Popped Element: %d", poppedItem));
        }
    }
}
