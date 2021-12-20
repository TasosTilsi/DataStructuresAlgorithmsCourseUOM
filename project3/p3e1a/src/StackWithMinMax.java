/******************************************************************************
 *  Compilation:  javac StackWithMinMax.java
 *  Dependencies: StdIn.java StdOut.java Stack.java
 *
 *
 ******************************************************************************/

//package edu.princeton.cs.algs4;

public class StackWithMinMax<Item extends Comparable<Item>> {
    private Stack<Item> elements;//Η πρώτη στοίβα
    private Stack<Item> mins;    //Η δεύτερη (min) στοίβα
    private Stack<Item> maxes;    //Η τριτη (max) στοίβα


    /*------------------------------ Constructor ---------------------------------*/
    public StackWithMinMax() {
        elements = new Stack<Item>();
        mins = new Stack<Item>();
        maxes = new Stack<Item>();
    }

    // για χρήση στην κλάση πελάτη
    public boolean isEmptyElements() {
        return elements.isEmpty();
    }


    /*----------------------------------- push -----------------------------------*/
    public void push(Item item) {
        elements.push(item);                //Το item εισάγεται στην elements
        //Πρέπει να ελέγξουμε εάν πρέπει να εισαχθεί και στην mins και στην maxes

        /*----------------------------------- Min Handling -----------------------------------*/
        if (!mins.isEmpty()) {               //Εάν η mins δεν είναι κενή
            //Αποθηκεύουμε το κορυφαίο στοιχείο της mins στην μεταβλητή temp, χωρίς να εξαχθεί από τη στοίβα
            Item temp = mins.peek();        // μπορεί να γίνει και χωρίς βοηθητική μεταβλητή
            if (item.compareTo(temp) <= 0)  //Εάν αυτό το εισερχόμενο item είναι μικρότερο ή ίσο του temp
                mins.push(item);            //Εισάγουμε και το item στην mins
            else
                mins.push(min());
        } else {                            //Εάν η mins είναι κενή
            mins.push(item);                //Το item εισάγεται στην mins (εφόσον είναι το μοναδικό θα είναι και το μικρότερο)
        }

        /*----------------------------------- Max Handling -----------------------------------*/
        if (!maxes.isEmpty()) {              //Εάν η maxes δεν είναι κενή
            //Αποθηκεύουμε το κορυφαίο στοιχείο της maxes στην μεταβλητή temp, χωρίς να εξαχθεί από τη στοίβα
            Item temp = maxes.peek();       // μπορεί να γίνει και χωρίς βοηθητική μεταβλητή
            if (item.compareTo(temp) >= 0)  //Εάν αυτό το εισερχόμενο item είναι μεγαλύτερο ή ίσο του temp
                maxes.push(item);           //Εισάγουμε και το item στην maxes
            else
                maxes.push(max());
        } else {                            //Εάν η maxes είναι κενή
            maxes.push(item);               //Το item εισάγεται στην maxes (εφόσον είναι το μοναδικό θα είναι και το μικρότερο)
        }
    }

    /*----------------------------------- pop -----------------------------------*/
    public Item pop() {
        if (!elements.isEmpty()) {           //Εάν η elements δεν είναι άδεια
            Item temp = elements.pop();     //Εξάγουμε το κορυφαίο στοιχείο από τη στοίβα elements στην μεταβλητή temp1
            mins.pop();                     //εξάγουμε το κορυφαίο στοιχείο της mins
            maxes.pop();                    //εξάγουμε το κορυφαίο στοιχείο της maxes
            //Δεν χρειάζεται να ελέγξουμε αν είναι άδεια η mins και η maxes. Εφόσον η elements δεν είναι άδεια,
            // η mins και η maxes θα περιέχει τουλάχιστον ένα στοιχείο

            /*if (temp1.compareTo(temp2) == 0)//Εάν τα 2 στοιχεία είναι ίσα
            {
                temp2 = mins.pop();
                return temp1;//Επιστρέφουμε το temp1 (ή το temp2 εφόσον είναι ίσα)
            } else//Εάν τα 2 στοιχεία δεν είναι ίσα
            {
                return temp1;//Επιστρέφουμε το temp1
            }*/
            return temp;
        } else
            return null;                    //Εάν η elements είναι άδεια, επιστρέφεται null
    }

    /*----------------------------------- min -----------------------------------*/
    public Item min() {
        if (!mins.isEmpty())                //Εάν η mins δεν είναι άδεια
            return mins.peek();             //Επιστρέφουμε το κορυφαίο στοιχείο της mins, χωρίς να το εξάγουμε

        return null;                        //Εάν η mins είναι άδεια, επιστρέφεται null
    }

    /*----------------------------------- max -----------------------------------*/
    public Item max() {
        if (!maxes.isEmpty())       //Εάν η maxes δεν είναι άδεια
            return maxes.peek();    //Επιστρέφουμε το κορυφαίο στοιχείο της mins, χωρίς να το εξάγουμε

        return null;                //Εάν η mins είναι άδεια, επιστρέφεται null
    }

    /*----------------------------------- size -----------------------------------*/
    public int size() {
        return elements.size();
    }

}
