/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

//package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly linked list with a static nested class for
 *  linked-list nodes. See {@link LinkedQueue} for the version from the
 *  textbook that uses a non-static nested class.
 *  See {@link ResizingArrayQueue} for a version that uses a resizing array.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this queue
 */
public class QueueCircular<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty queue.
     */
    public QueueCircular() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(QueueCircular<Item> q,Item item) {
    	Node<Item> temp = new Node<>();
    	temp.item = item;
    	if (q.first == null)
    		q.first=temp;
    	else
    		q.last.next = temp;
    	
    	q.last = temp;
    	q.last.next = q.first;
    	
    	//old implementation
    	/******************************
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
    	********************************/
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue(QueueCircular<Item> q) {
    	
    	if (q.first == null) {
    		StdOut.println("Queue is Empty");
    		return null;	
    	}
    	
    	Item value;
    	if (q.first == q.last) {
    		value = q.first.item;
    		q.first = null;
    		q.last = null;
    	}else {
    		Node<Item> temp = q.first;
    		value = (Item) temp.item;
    		q.first = q.first.next;
    		q.last.next = q.first;
    	}
    	n--;
    	return value;
    	
    	//old implementation
    	/************************************************
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
        ***************************************************/
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString(QueueCircular<Item> q) {
    	StringBuilder s = new StringBuilder();
    	Node<Item> temp = q.first;
    	while (temp.next != q.first) {
//    		StdOut.print(temp.item +" ");
    		s.append(temp.item);
            s.append(' ');
    		temp = temp.next;
    	}
    	s.append(temp.item);
    	return s.toString();
    	
    	//old implementation
    	/************************************************
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
        ************************************************/
    } 
    
    /**
     * Returns a string while dequeuing the queue 
     * @param queue
     * @return the sequence of items in FIFO order while dequeuing the queue, separated by spaces
     */
    public String displayDequeue(QueueCircular<Item> queue) {
    	StringBuilder s = new StringBuilder();
    	while (queue.size()!=0) {
    		s.append(dequeue(queue));
    		s.append(' ');
    	}
    	
    	return s.toString();
    	
    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the {@code Queue} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        QueueCircular<String> queue = new QueueCircular<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-"))
//                queue.enqueue(queue,item);
//            else if (!queue.isEmpty())
//                StdOut.print(queue.dequeue(queue) + " ");
//        }
//        StdOut.println("(" + queue.size() + " left on queue)");
        
        queue.enqueue(queue, "14");
        queue.enqueue(queue, "22");
        queue.enqueue(queue, "6");
  
        // Display elements present in Circular Queue
        StdOut.println("queue size " + queue.size());
        StdOut.println(queue.toString(queue));
  
        // Deleting elements from Circular Queue
        System.out.printf("\nDeleted value = %s", queue.dequeue(queue));
        System.out.printf("\nDeleted value = %s", queue.dequeue(queue));
  
        // Remaining elements in Circular Queue
        StdOut.println("\nqueue size " + queue.size());
        StdOut.println(queue.toString(queue));
  
        queue.enqueue(queue, "9");
        queue.enqueue(queue, "20");
        StdOut.println("queue size " + queue.size());
        StdOut.println(queue.toString(queue));
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
