package model;

/**
 * Represents a generic node in a doubly linked list with pointers to the next
 * and previous nodes.
 * 
 * @param <T> the type of data stored in the node
 */
public class Node<T> {

    private T info;
    private Node<T> next;
    private Node<T> previous;

    /**
     * Default constructor for the Node class.
     */
    public Node() {
    }

    /**
     * Constructs a new Node with the specified information.
     * 
     * @param info the data to be stored in the node
     */
    public Node(T info) {
        this.info = info;
        this.next = null;
        this.previous = null;
    }

    /**
     * Gets the information stored in the node.
     * 
     * @return the data stored in the node
     */
    public T getInfo() {
        return info;
    }

    /**
     * Sets the information to be stored in the node.
     * 
     * @param info the new data to store in the node
     */
    public void setInfo(T info) {
        this.info = info;
    }

    /**
     * Gets the reference to the next node.
     * 
     * @return the next node in the list
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node.
     * 
     * @param next the new next node in the list
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Gets the reference to the previous node.
     * 
     * @return the previous node in the list
     */
    public Node<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the reference to the previous node.
     * 
     * @param previous the new previous node in the list
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

}
