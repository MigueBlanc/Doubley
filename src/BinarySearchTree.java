/**
 *  AUTHOR: MICHAEL BLANCO
 *  Statement of Authenticity: All extra methods added are product of my own work.\
 *  date : 07/27/2022
 */

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 *
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {




    //PROGRAM TESTING
    /**             visual tree
     *                      100
     *                      / \
     *                   40    150
     *                  /  \     \
     *                 30  50    160
     *                      \       \
     *                       70      200
     *                         \
     *                          90
     */
    public static void main(String[] args) {
        int[] myArr = {100, 40,30, 50, 150,70, 160, 200, 90,60,65,63};
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        for(int i = 0; i < myArr.length; i++){
            t.insert(myArr[i]);
        }
        System.out.println("In-order printing of tree: ");
        t.printTree();


        System.out.println("Nodes with only one child: (should be 4) =  " + t.nodesWithOneChild());


        System.out.println("Printing tree in level-order : ");
        t.printLevelOrder();

        System.out.println("Height of tree is " + t.height());
        System.out.println(t.printInOrder());


    }

    public String printInOrder(){
       printInorder(root);

        return "end";
    }

    private void printInorder(BinaryNode<AnyType> t1){
        if(t1 == null){
            return;

        }
        /* first recur on left child */
        printInorder(t1.left);
        /* then print the data of node */
        System.out.print(t1.element + " ");
        /* now recur on right child */
        printInorder(t1.right);


    }



    /**
     * this public method calls the private method
     * @return int
     */
    public int nodesWithOneChild() {

        return nodesWithOneChild(root);
    }

    /**
     *
     * @param e1
     * @return
     */
    private int nodesWithOneChild(BinaryNode<AnyType> e1) {
        int acc;

        if (e1 == null) {
            return 0;
        } else if (e1.left == null && e1.right != null) {
            acc = nodesWithOneChild(e1.right) + 1;
        } else if (e1.left != null && e1.right == null) {
            acc = nodesWithOneChild(e1.left);
        } /*else if (e1.left == null && e1.right == null){
            acc =+ 0;
            }*/
        else {
            acc = nodesWithOneChild(e1.left) + nodesWithOneChild(e1.right);
        }
        return acc;
    }

    /**
     * this method prints the tree in level-order
     *
     */
    private void printLevelOrder() {
        DoublyLinkedList<BinaryNode> myQueue = new DoublyLinkedList<>();
        myQueue.addFirst(root);


        while(!myQueue.isEmpty()){

            @SuppressWarnings("rawtypes") BinaryNode pointer = myQueue.getLast();

            // dequeue node:
            myQueue.removeLast();
            System.out.println(pointer.element);
            // case 1: node's left child is not null, enqueue it.
            if(pointer.left != null){
                myQueue.addFirst(pointer.left);
            }

            //case 2: node's right child is non-empty, enqueue  it.

            if(pointer.right != null){
                myQueue.addFirst(pointer.right);
            }
        }


    }



    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;


    /**
     * Construct the tree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x) {
        root = insertRec(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        if (isEmpty())
            return null;
        //throw new UnderflowException();
        return findMin(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax() {

        if (isEmpty())
            return null;
        // throw new UnderflowException();
        return findMax(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insertRec(AnyType x, BinaryNode<AnyType> t) {
       if (t == null) {
            return new BinaryNode<>(x, null, null);

        }
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insertRec(x, t.left);
        if (compareResult > 0)
            t.right = insertRec(x, t.right);
            // Duplicate; do nothing
        else if (compareResult == 0 )
            System.out.println("I HAPPENED DUDE");


        return t;
    }





    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
public int height(){
        return height(root);
}



private int height(BinaryNode<AnyType> e1) {


    int leftHeight = 0;
    int rightHeight = 0;

    if (e1.left != null) {
        leftHeight = height(e1.left);
    }
    if (e1.right != null) {
        rightHeight = height(e1.right);
    }

    return Math.max(leftHeight, rightHeight) + 1;
}


    /**
     * Internal method to compute height of a subtree.
     *
     * @param t the node that roots the subtree.
     */
    //private int height(BinaryNode<AnyType> t) {
        //if (t == null)
           // return -1;
        //else
           // return 1 + Math.max(height(t.left), height(t.right));
   // }


}


