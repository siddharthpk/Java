/**
Name: Siddharth Pathak
Student #: V00876495
**/

import java.util.NoSuchElementException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class RedBlackBST {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST
    private int numRedNodes = 0;

    // BST helper node data type
    private class Node {
        private int key;           // key
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(int key, boolean color, int size) {
            this.key = key;
            this.color = color;
            this.size = size;
        }
    }

	public RedBlackBST() {

	}
   /***************************************************************************
    *  Node helper methods.
    ***************************************************************************/
    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }


    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

   /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

   /***************************************************************************
    *  Red-black tree insertion.
    ***************************************************************************/

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public void put(int key) {
        root = put(root, key);
        if(isRed(root)) {
            numRedNodes -=1;
        }
        root.color = BLACK;
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, int key) {
        if (h == null) {
            numRedNodes++;
            return new Node(key, RED, 1);
        }

        int cmp = key - h.key;
        if      (cmp < 0) h.left  = put(h.left,  key);
        else if (cmp > 0) h.right = put(h.right, key);
        else              h.key   = key;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left)  &&  isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left)  &&  isRed(h.right)) {
            flipColors(h);
            numRedNodes--;
        }
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }


   /***************************************************************************
    *  Red-black tree helper functions.
    ***************************************************************************/

    // make a left-leaning link lean to the right
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private int numRed(Node h) {
        int numR = 0;

        if(h != null) {
            if(h.color)
                numR = 1;

            numR += numRed(h.left);
            numR += numRed(h.right);
        }

        return numR;
    }

    /**
     * calculates the percentage of red nodes in the RedBlack Search Tree
     * @return double  percentage of red nodes in the tree
     */
    public double percentRed() {
        
        double size = this.size();
        if(size > 0)
            return ((double) numRedNodes / size)*100;
        else
            return 0;
    }

    public static void printExpVals() {
        double percentRed;
        RedBlackBST stHyp = new RedBlackBST();
        ArrayList<Integer> keys;
        int[] tests = {10000, 100000, 1000000};

        double percentageSum = 0;
        int numTrials = 200;
        double[] expValues = new double[tests.length];
        int key;

        int col = 0;

        for(int testNum : tests) {
            for(int j = 0; j < numTrials; j++) {

                keys = new ArrayList<Integer>();
                for (int i = 0; i < testNum; i++) {
                    keys.add(new Integer(i));
                }

                Collections.shuffle(keys);

                for(int i = 0; i < testNum; i++) {
                    key = keys.get(i);
                    stHyp.put(key);
                }

                percentRed = stHyp.percentRed();
                expValues[col] += percentRed;

                stHyp = new RedBlackBST();
                // System.out.println("--------END OF TRIAL---------");
            }

            expValues[col] = expValues[col] / numTrials;
            System.out.printf("The Expected value for n = %d is: %f\n", testNum, expValues[col]);
            col++;
        }
    }

    
    public static void main(String[] args) throws IOException {
        double sTime = System.nanoTime();

        if(args.length > 0) {
            String[] strVals;
            String l;
            RedBlackBST st = new RedBlackBST();

            System.out.printf("Reading input values from: %s\n", args[0]);
            FileReader in = new FileReader(args[0]);
            BufferedReader buf = new BufferedReader(in);

            l = buf.readLine();
            in.close();

            strVals = l.split("\\s+");

            for(String str : strVals) {
                st.put(Integer.parseInt(str));
            }

            System.out.printf("Percent of Red Nodes: %f\n", st.percentRed());
        } else {
            printExpVals();
        }

        double eTime   = System.nanoTime();
        double total_Time = (eTime - sTime);
        double total_Time_Seconds = (total_Time*1.0) / 1000000000.0;
        System.out.println(total_Time_Seconds);
    }


}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
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