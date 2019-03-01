import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Median Updates: AVL tree
 * 
 *
 * https://www.hackerrank.com/challenges/median/problem
 * */


class Node {
    int val, height;
    Node left, right;

    Node (int value){
        val = value;
        height = 1;
    }
}

class AVLTree {

    Node root;
    int size;

    AVLTree (){
        size = 0;
    }

    int getSize (){
        return size;
    }

    int height (Node n) {
        if (n == null)
            return 0;
        return n.height;
    }

    Node insert (int val){
        size++;
        //System.out.println("inserting "+ val );
        return root = insert(root, val);
    }

    Node insert (Node current, int val){
        if (current == null){
            return new Node (val);
        }else if (val <= current.val){
            current.left = insert (current.left, val);
        }else {
            current.right = insert (current.right, val);
        }
        current.height = 1 + Math.max (height (current.left), height(current.right));
        return balancing (current);
    }

    boolean contains (int val) {
        Node find = get (root, val);
        if (find == null)
            return false;
        return true;
    }

    Node get (Node current, int val) {
        if (current == null) {
            return null;
        }else if (val < current.val){
            return get (current.left, val);
        }else if (val > current.val) {
            return get (current.right, val);
        }else
            return current;
    }


    void remove (int val){
        size--;
        root = remove (root, val);
    }

    Node remove (Node current, int val) {
        if (current == null) {
            return current;
        }

        if (val < current.val) {
            current.left = remove (current.left, val);
        }else if (val > current.val) {
            current.right = remove (current.right, val);
        }else { // remove from the tree
            if (current.left == null) { // only right child
                return current.right;
            }else if (current.right == null) { // only left child
                return current.left;
            }else { // two children
                Node temp = getMinSubTree (current.right); // find the minimum of right subtree
                current.val = temp.val;
                current.right = remove (current.right, temp.val);
            }
        }

        current.height = 1 + Math.max(height (current.left), height(current.right));
        return balancing (current);
    }

    Node getMinSubTree (Node n) {
        Node current = n;
        while (current.left !=null) {
            current = current.left;
        }
        return current;
    }

    // Get balance factor
    int getBF (Node n) {
        return height (n.left) - height (n.right);
    }

    // Balancing the tree
    Node balancing (Node n){
        if (getBF(n) > 1) {
            if (getBF(n.left) < 0) { // left-right
                n.left = leftRotate (n.left);
            }
            n =  rightRotate (n); // left-left
        }else if (getBF(n) < -1) {
            if (getBF(n.right) > 0) { // right-left
                n.right = rightRotate (n.right);
            }
            n = leftRotate (n); // right-right
        }
        return n;
    }

    /* Left Rotation for right-right case
     *  1
     *      2    to    2
     *   4    3      1    3
     *                 4
     * */
    Node leftRotate (Node one) {
        Node two = one.right;
        Node four = two.left;
        two.left = one;
        one.right = four;
        one.height = Math.max(height (one.left), height(one.right)) + 1;
        two.height = Math.max(height (two.left), height (two.right)) + 1;
        return two;
    }


    /* Right Rotation for left-left case
     *      3
     *   2    to         2
     * 1   4         1       3
     *                        4
     * */
    Node rightRotate (Node three) {
        Node two = three.left;
        Node four = two.right;
        two.right = three;
        three.left = four;
        three.height = Math.max(height (three.left), height(three.right)) + 1;
        two.height = Math.max(height (two.left), height (two.right)) + 1;
        return two;
    }

    // gives the median of the tree
    double median (){
        ArrayList <Integer> sorted = new ArrayList <Integer> ();
        order (root, sorted);
        int s = sorted.size();
        if (sorted.size() % 2 == 1) { // when the size of tree is odd
            return sorted.get((int)s/2);
        }else { // when the size of tree is even
            return ((double) (sorted.get(s/2-1)) + (double)(sorted.get(s/2)))/2;
        }
    }

    void order (Node node, ArrayList <Integer> result) {
        if (node != null) {
            order (node.left, result);
            result.add(node.val);
            order (node.right, result);
        }
    }

    void preOrder(Node node) {
        if (node != null)
        {
            System.out.print("{root: "+node.val+"}" + " ");
            System.out.print(" l- ( "+node.val+")");
            preOrder(node.left);
            System.out.print(" r-(" + node.val +")");
            preOrder(node.right);
        }
    }

}

class MedianUpdates{

    static void median(String a [], int x []) {
        StringBuilder sb = new StringBuilder (); // expected the output
        // build AVL tree keep track what is in there or not
        AVLTree bbst = new AVLTree ();

        for (int i = 0; i < x.length; i++){
            if (a[i].equals("a")){
                bbst.insert(x[i]);
                double median = bbst.median();
                if ((int) median == median) {
                    sb.append ((int) median+ "\n");
                } else sb.append(String.format("%.1f\n", median));

            }else if (a[i].equals("r")){
                if (bbst.getSize() <= 0) {
                    sb.append ("Wrong!\n");
                }else if (!(bbst.contains(x[i]))){
                    sb.append ("Wrong!\n");
                } else {
                    bbst.remove(x[i]);
                    if (bbst.getSize() <= 0) {
                        sb.append ("Wrong!\n");
                    }else {
                        double median = bbst.median();
                        if ((int) median == median) {
                            sb.append ((int) median+ "\n");
                        } else sb.append(String.format("%.1f\n", median));
                    }
                }
            }

        }

        System.out.println(sb.toString());
    }

    public static void main( String args[] ){

        Scanner in = new Scanner(System.in);

        int N;
        N = in.nextInt();

        String s[] = new String[N];
        int x[] = new int[N];

        for(int i=0; i<N; i++){
            s[i] = in.next();
            x[i] = in.nextInt();
        }

        median(s,x);

        in.close();
    }
}
