/******************************************************************************
 * Compilation: javac P4e1.java
 * Execution: java p4e1 Tasos < ../resources/in-p4e1.txt > ../resources/out-p4e1_1.txt
 * Execution: java p4e1 TasosTilsi < ../resources/in-p4e1.txt > ../resources/out-p4e1_2.txt
 * Execution: java p4e1 Anastasios1 < ../resources/in-p4e1.txt > ../resources/out-p4e1_3.txt
 * Execution: java p4e1 Anastasios1Tilsizoglou < ../resources/in-p4e1.txt > ../resources/out-p4e1_4.txt
 * Execution: java p4e1 Anastasiosss1Tasos < ../resources/in-p4e1.txt > ../resources/out-p4e1_5.txt
 * Dependencies: PasswordChecker.java SeparateChainingHashST.java SequentialSearchST.java Queue.java StdIn.java StdOut.java
 * Data files: ../resources/in-p4ex.txt
 ******************************************************************************/

public class p4e1 {

    public static void main(String[] args) {

        String pass = "hello2world";
        if (args.length != 0) {
            pass = args[0]; //passed argument from command line execution if exists
        }

        PasswordChecker checker = new PasswordChecker();

        if (checker.isValid(pass)){
            StdOut.println("");
            StdOut.println(pass + " = Good Password");
        }else{
            StdOut.println("");
            StdOut.println(pass + " = Bad Password");
        }
    }

}
