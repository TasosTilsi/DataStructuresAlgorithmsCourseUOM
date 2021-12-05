public class StacksHandler {

    private int min = -1; // sentinel value for min
    private Stack<Integer> stack = new Stack<>();
    private MinimumStack<Integer> minimumStack= new MinimumStack<>();

    public void push(int val)
    {
        // if stack is empty initialize it
        if (minimumStack.isEmpty()) {
            min = val;
            stack.push(val);
            minimumStack.push(min);
        }else {
            stack.push(val);
            if (val <= min) {
                min = val;
                minimumStack.push(min);
            }
        }
    }

    public int pop()
    {
        // if stack is empty notice the user
        if (minimumStack.isEmpty()) {
//            StdOut.println("Minimum Stack is empty!");
            return -1;
        }

        int poppedItem = stack.pop();
        if(poppedItem == minimumStack.peek()){
            minimumStack.pop();
            if(!minimumStack.isEmpty())
                min = minimumStack.peek();
        }
        return poppedItem;
    }

    public int min(){
        if (minimumStack.isEmpty()){
//            StdOut.println("Minimum Stack is empty!");
            return -1;
        }
        return minimumStack.peek();
    }

    // Test Code
    public static void main(String[] args)
    {
        StacksHandler s = new StacksHandler();

        int[] arr = { 4, 3, 5, 3, 2, 1 };

        for (int i = 0; i < arr.length; i++) {
            s.push(arr[i]);
            StdOut.println("s.push(arr[i]) " + arr[i]);
//            s.min();
            StdOut.println("s.min() "+ s.min());
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {

            StdOut.println("s.pop() " + s.pop());
//            s.min();
            StdOut.println("s.min() "+ s.min());
        }
    }
}
