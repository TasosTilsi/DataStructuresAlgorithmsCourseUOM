/******************************************************************************
 * Compilation: javac p2e1.java
 * Execution:
 java p2e1 < in-p2e1.txt > out-p2e1.txt
 * Dependencies: StdIn.java StdOut.java Stack.java MinimumStack.Java StacksHandler.java
 * Data files:
 in-p2e1.txt
 *
 ******************************************************************************/

public class p2e1 {

    public static void main(String[] args) {
        chosenAction();
    }

    private static void printMenu(){

        StdOut.println("Menu");
        StdOut.println("1 - Push an item");
        StdOut.println("2 - Pop an item");
        StdOut.println("3 - Get the Minimum Item");
        StdOut.println("4 - Exit");

    }

    /*private static int[] readChoices(){
        int choices[] = {4};
        while (!StdIn.isEmpty()){
            choices = StdIn.readAllInts();
        }
        return choices;
    }*/

    private static void chosenAction(){
        int choice = 4;
        int itemToPush=0;
        int poppedItem;
        int minimumItem;
        StacksHandler handler = new StacksHandler();

        do {
            printMenu();
            StdOut.print("Choice: ");
            choice = StdIn.readInt();
            StdOut.print(choice);
            StdOut.println();
            switch (choice) {
                case 1:
                    StdOut.print("Item to push: ");
                    itemToPush = StdIn.readInt();
                    StdOut.print(itemToPush);
                    StdOut.println();
                    handler.push(itemToPush);
                    break;
                case 2:
                    poppedItem = handler.pop();
                    if(poppedItem == -1){
                        StdOut.println("Minimum Stack is empty!");
                    }else {
                        StdOut.println("Popped Item: " + poppedItem);
                    }
                    break;
                case 3:
                    minimumItem = handler.min();
                    if(minimumItem == -1){
                        StdOut.println("Minimum Stack is empty!");
                    }else {
                        StdOut.println("Minimum Item: " + minimumItem);
                    }
                    break;
                default:
                    break;
            }
        }while(choice!=4);
    }
}
