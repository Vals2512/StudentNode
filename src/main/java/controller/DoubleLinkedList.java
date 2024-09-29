package controller;

import java.util.ArrayList;
import java.util.List;

import model.Entity;
import model.Node;

/**
 * A circular doubly linked list implementation.
 *
 * @param <T> the type of elements in this list
 */
public class DoubleLinkedList<T extends Entity> {

    private Node<T> head;
    private Node<T> last;

    /**
     * Initializes an empty list.
     */
    public DoubleLinkedList() {
        this.head = null;
        this.last = null;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list and the last are empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null && last == null;
    }

    /**
     * Adds a node at the beginning of the list.
     *
     * @param value the value to be added
     */
    public void addNodeFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            last = newNode;

        } else {

            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;
        }
    }

    /**
     * Adds a node at the end of the list.
     *
     * @param value the value to be added
     */
    public void addNodeLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            last = newNode;

        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
    }

    /**
     * Adds a node after the specified node.
     *
     * @param node  the node after which to add the new node
     * @param value the value to be added
     */
    public void addNodeAfterTo(Node<T> node, T value) {
        if (node == null)
            return; // Validate input
        Node<T> newNode = new Node<>(value);

        if (node == last) {
            addNodeLast(value);
        } else {
            newNode.setNext(node.getNext());
            newNode.setPrevious(node);
            if (node.getNext() != null) {
                node.getNext().setPrevious(newNode);
            }
            node.setNext(newNode);
        }
    }

    /**
     * Adds a node before the specified node.
     *
     * @param node  the node before which to add the new node
     * @param value the value to be added
     */
    public void addNodeBeforeTo(Node<T> node, T value) {
        if (node == null)
            return;
        Node<T> newNode = new Node<>(value);

        if (node == head) {
            addNodeFirst(value);
        } else {
            newNode.setNext(node);
            newNode.setPrevious(node.getPrevious());
            if (node.getPrevious() != null) {
                node.getPrevious().setNext(newNode);
            }
            node.setPrevious(newNode);
        }
    }

    /**
     * Adds a node in sorted order.
     *
     * @param value the value to be added
     */

    public void addNodeSorted(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            last = newNode;
        } else {
            Node<T> current = head;
            while (current != null) {
                if (value.compareTo(current.getInfo()) < 0) {
                    if (current == head) {
                        addNodeFirst(value);
                    } else {
                        addNodeBeforeTo(current, value);
                    }
                    return;
                }
                current = current.getNext();
            }
            addNodeLast(value);
        }
    }

    /**
     * Finds a node with the specified value.
     *
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     */

    public Node<T> findNode(String code) {
        Node<T> current = head;
        while (current != null) {
            if (current.getInfo().getId().equals(code)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Finds the info associated with the specified value.
     *
     * @param value the value to search for
     * @return the info of the node containing the value, or null if not found
     */
    public T findInfo(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.getInfo().equals(value)) {
                return current.getInfo();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Gets the elements of the linked list in a specified order.
     *
     * @param asc true to get elements in ascending order, false for descending
     *            order
     * @return a list of elements in the specified order
     */
    public List<T> getLinkedList(boolean asc) {
        List<T> elements = new ArrayList<>();
        Node<T> current;

        if (asc) {
            current = head;
            while (current != null) {
                elements.add(current.getInfo());
                current = current.getNext();
            }
        } else {
            current = last;
            while (current != null) {
                elements.add(current.getInfo());
                current = current.getPrevious();
            }
        }

        return elements;
    }

    /**
     * Deletes a specified node from the list.
     *
     * @param node the node to be deleted
     * @return the information of the deleted node
     */
    public T deleteNode(Node<T> node) {
        if (node == head && node == last) {
            T info = node.getInfo();
            head = null;
            last = null;
            return info;
        } else if (node == head) {
            head = head.getNext();
            last.setNext(head);
            head.setPrevious(last);
        } else if (node == last) {
            last = last.getPrevious();
            last.setNext(head);
            head.setPrevious(last);
        } else {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        }
        
        node = null;
        return node.getInfo();
    }

    /**
     * Retrieves the size of the list.
     *
     * @return the number of elements in the list
     */
    public int getSize() {
        int size = 0;
        Node<T> current = head;
        if (current == null)
            return size;

        do {
            size++;
            current = current.getNext();
        } while (current != head && current != null);

        return size;
    }

    /**
     * Retrieves the object at the specified position.
     *
     * @param pos the position to retrieve
     * @return the object at the specified position, or null if out of bounds
     */
    public T getObject(int pos) {
        Node<T> current = head;
        int index = 0;
        if (current == null)
            return null;

        do {
            if (index == pos) {
                return current.getInfo();
            }
            index++;
            current = current.getNext();
        } while (current != head);

        return null;
    }

    /**
     * Retrieves the first element in the list.
     *
     * @return the first element, or null if the list is empty
     */
    public T getFirst() {
        return head != null ? head.getInfo() : null;
    }

    /**
     * Retrieves the last element in the list.
     *
     * @return the last element, or null if the list is empty
     */
    public T getLast() {
        return last != null ? last.getInfo() : null;
    }
}
