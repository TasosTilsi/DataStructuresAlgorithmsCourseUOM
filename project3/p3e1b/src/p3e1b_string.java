/******************************************************************************
 * Compilation: javac p3e1b_string.java
 * Execution: java p3e1b_string < ../resources/in-p3e1b-string.txt > ../resources/out-p3e1b-string.txt
 * Dependencies: StdIn.java StdOut.java StackWithMinMax.java
 * Data files: in-p3e1b-string.txt
 ******************************************************************************/
public class p3e1b_string {

    public static void main(String[] args) {
        StackWithMinMax myStack = new StackWithMinMax<String>();

        readItems(myStack);

        removeItems(myStack);
    }

    private static void readItems(StackWithMinMax myStack) {
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();  //reads input from Standard Input
            myStack.push(value);                //push strings into stack
            StdOut.println(String.format("Top Element: %s min: %s max: %s size = %d", value, myStack.min(), myStack.max(), myStack.size()));
        }
    }

    private static void removeItems(StackWithMinMax myStack) {
        StdOut.println(String.format("Popping each element of the stack"));
        while(myStack.size()>0){
            String max = (String) myStack.max();
            String min = (String) myStack.min();
            int size = myStack.size();
            String poppedItem = (String) myStack.pop();
            StdOut.println(String.format("Top Element: %s min: %s max: %s size = %d", poppedItem, min, max, size));
            StdOut.println(String.format("Popped Element: %s", poppedItem));
        }
    }
}
