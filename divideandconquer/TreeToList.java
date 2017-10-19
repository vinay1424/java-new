package com.maloo.divideandconquer;


import com.maloo.trees.BST;
import static  com.maloo.trees.BST.Node;

public class TreeToList {
    /**
     * This method is the core method which is getting called recursively
     * It gets the list from left sub tree, and right subtree
     * finally appends leftlist<->root<->rightList in recursive manner
     *
     * @param root
     * @return
     */
    public static Node<Integer> treeToList(Node<Integer> root) {
        //for base case ...  which is leaves
        if(root ==null) {
            return null;
        }

        //get the list of left side
        Node<Integer> leftList = treeToList(root.left);
        //get the list of right side
        Node<Integer> rightList = treeToList(root.right);

        //set the left child and right child of root as root
        // since they will be replaced by end of a list and beginning of b list
        //also root is a single noded list .. hence to do list appending make root as a 3 mode list
        root.left = root;
        root.right = root;

        //append left list with root node
        //make sure the bidirectional relationship is taken care of
        leftList = append(leftList,root);
        // append the right node to the left+root to keep it sorted
        leftList = append(leftList, rightList);

        return leftList;
    }

    /**
     * this method appends the two circular linked list
     * given the heads of both circular linked list
     * @param a
     * @param b
     * @return
     */
    private static Node<Integer> append(Node<Integer> a, Node<Integer> b) {
        //if either of them are null return the one which is not null
        if(a==null){
            return b;
        }
        if(b==null) {
            return a;
        }

        // go to the last node of list A ..since its circular you can find it in head's left
        Node<Integer> aPrevious = a.left;
        // go to the last node of list B ..since its circular you can find it in head's left
        Node<Integer> bPrevious = b.left;

        //append b to the last node of a .. establish the link
        join(aPrevious,b);

        // append a to the last node of b.. to make it circular
        join(bPrevious,a);

        // return the joined head
        return a;
    }

    /**
     * It establishes the bidirectional link between
     * @param a
     * @param b
     */
    private static void join(Node<Integer> a, Node<Integer> b) {
        // creating the link
        a.right= b;
        b.left = a;
    }

    /**
     * prints a double linked list
     * @param head
     */
    public static void printList(Node<Integer> head) {
        Node<Integer> current = head;

        while (current != null) {
            System.out.print(Integer.toString(current.data) + " ");
            current = current.right;
            if (current == head) break;
        }

        System.out.println();
    }


    public static void main(String args[]) {
        BST<Integer> bst  = new BST<Integer>();
        bst.insert(4);
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);

        printList(treeToList(bst.root));

    }


}
