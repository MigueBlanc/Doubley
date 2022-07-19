/**
 * EDITOR : MICHAEL BLANCO
 * ALL METHODS THAT WERE ADDED USED A HELPER FUNCTION INSIDE THE METHOD..
 *
 *
 *
 */

/**
 * LinkedList class implements a doubly-linked list.
 * This doubly-linked list implementation uses dummy first and
 * last nodes - that is these nodes do not store data - just
 * eliminate some edge conditions.
 * <p>
 * AnyType can be any Object type.
 * <p>
 * Note: This is a simplified version of the MyLinkedList class in the Weiss textbook,
 * modified by R. McCauley
 */
class DoublyLinkedList<AnyType> {

    /**********************************************************************
     * This is the doubly-linked list node - implemented via an inner class, so
     * its fields are publicly available to the DoublyLinkedList methods
     */
    private static class Node<AnyType> {


        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;



  //Node constructor  DATA TYPE / PREV NODE    /   NEXT NODE
        public Node(AnyType d, Node<AnyType> prev, Node<AnyType> nxt) {
            data = d;
            this.prev = prev;
            next = nxt;
        }
    }

    /**********************************************************************/

    // Doubly linked list instance variables

    private int theSize;        // keeps track of how many elements stored
    private Node<AnyType> head; // head node contains no data
    private Node<AnyType> tail; // tail node contains no data


    /**
     * Constructor for an empty LinkedList.
     */
    public DoublyLinkedList() {
        doClear();
    }

    private void clear() {
        doClear();
    }

    /**
     * Change the size of this collection to zero.
     */
    public void doClear() {
        // create a new head node
        head = new Node<>(null, null, null);
        // create a new tail node and make it reference the head node
        tail = new Node<>(null, head, null);
        // make the head node reference the tail node
        head.next = tail;

        theSize = 0; // set size to reflect empty list

    }


    /**
     *
     */

    /**
     * Returns the number of items in this collection.
     *
     * @return the number of items in this collection.
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an item to the end of this collection.
     *
     * @param x any object.
     * @return true.
     */
    public boolean add(AnyType x) {
        add(size(), x); // it's using the size as a parameter because that's the number to place node at end of list.
        return true;
    }

    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     *
     * @param x   any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add(int idx, AnyType x) {
        addBefore(getNode(idx, 0, size()), x);
    }

    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     *
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p); /// creates a Node object, initializes it using the parameters
                                                          // passed.
                                                         // we use p.prev because p.prev is the now the previous node of
                                                        // our current node.
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }


    /**
     * Returns the item at position idx.
     *
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get(int idx) {
        return getNode(idx).data;
    }

    /**
     * Changes the item at position idx.
     *
     * @param idx    the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set(int idx, AnyType newVal) {
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     *
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     *
     * @param idx   index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */
    private Node<AnyType> getNode(int idx, int lower, int upper) {
        Node<AnyType> p;

        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

        if (idx < size() / 2) {
            p = head.next;
            for (int i = 0; i < idx; i++)
                p = p.next;
        } else {
            p = tail;
            for (int i = size(); i > idx; i--)
                p = p.prev;
        }

        return p;
    }

    /**
     * Removes an item from this collection.
     *
     * @param idx the index of the object.
     */
    public void remove(int idx) {
        remove(getNode(idx));
    }

    /**
     * Removes the object contained in Node p.
     *
     * @param p the Node containing the object.
     */
    private void remove(Node<AnyType> p) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;

    }

    /**
     * Returns a String representation of this collection.
     */
    public String toString() {

        StringBuilder sb = new StringBuilder("Tail to front" + "[ ");
        Node<AnyType> walker = tail.prev; // start at first data node
        while (walker != head) {
            sb.append(walker.data).append(" ");
            walker = walker.prev;
        }
        sb.append("]");

        sb.append(" Front to tail" + " [");
        Node<AnyType> walkerFront = head.next;
        while (walkerFront != tail) {
            sb.append(walkerFront.data).append(" ");
            walkerFront = walkerFront.next;
        }
            sb.append("]");


//        sb.append("[");
//        Node<AnyType> walkerBack = tail.prev; // start at last data node
//        while (walker != head) {
//            while (walker != head) {
//            walker = walkerBack.prev;
//        }
//        sb.append("]");

        return new String(sb);
    }





    /**
     * adds an element to the beginning of the list.
     * @param el
     */
    public void addFirst(AnyType el) {
        Node<AnyType> newNode = new Node<>(el, head, head.next );
        head.next.next.prev = newNode;
        head.next = newNode;
        theSize++;
    }

    /**
     * adds an element to the end of the list
     * @param el
     */
    public void addLast(AnyType el){
        Node<AnyType> newNode = new Node<>(el, tail.prev, tail);
        tail.prev.next = newNode;
        tail.prev = newNode;
        theSize--;
    }


    /**
     * removes the first element of the list.
     */

    public void removeFirst(){
        head.next = head.next.next;
        head.next.prev = head;
    }


    /**
     * removes the last element of the list.
     */
    public void removeLast() {
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;

    }

    /**
     * returns the first element of the list.
     * @return
     */
    public AnyType getFirst(){
        return head.next.data;
    }


    /**
     * returns the last element of the list.
     * @return
     */
    public AnyType getLast(){
        return tail.prev.data;
    }



    // main method to test DoublyLinkedList
    public static void main(String[] args) {
        DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();

        for (int i = 0; i < 10; i++)
            lst.add(i);
        System.out.println("After adding 0 through 9");
        System.out.println(lst);
        for (int i = 10; i <= 100; i+= 10)
            lst.add(0, i);
        System.out.println("After adding 20 through 29, each at position 0");
        System.out.println(lst);

        lst.remove(0);
        lst.remove(lst.size() - 1);
        System.out.println("After removing the first and the last elements");
        System.out.println(lst);
        lst.addFirst(100);
        lst.addLast(9);


        System.out.println("AFTER METHODS!!!");
        System.out.println(lst);


        lst.removeLast();
        lst.removeFirst();
        System.out.println("SECOND ROUND: 100 AND 50 SHOULD BE GONE");
        System.out.println(lst);
        System.out.println("return first element " + lst.getFirst());
        System.out.println("return last element " + lst.getLast());










    }







}
