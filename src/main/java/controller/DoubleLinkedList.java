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
            addNodeLast(value); // If it's the last node, use addNodeLast
        } else {
            newNode.setNext(node.getNext()); // Set newNode's next to node's next
            newNode.setPrevious(node); // Set newNode's previous to node
            if (node.getNext() != null) { // Ensure node's next is not null
                node.getNext().setPrevious(newNode); // Set node's next previous to newNode
            }
            node.setNext(newNode); // Set node's next to newNode
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
            return; // Validate input
        Node<T> newNode = new Node<>(value);

        if (node == head) {
            addNodeFirst(value); // If it's the head node, use addNodeFirst
        } else {
            newNode.setNext(node); // Set newNode's next to node
            newNode.setPrevious(node.getPrevious()); // Set newNode's previous
            if (node.getPrevious() != null) {
                node.getPrevious().setNext(newNode); // Set the previous node's next to newNode
            }
            node.setPrevious(newNode); // Set node's previous to newNode
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
            last = newNode; // Initialize head and last
        } else {
            Node<T> current = head;
            while (current != null) {
                // Compare the values and insert the new node in the correct position
                if (value.compareTo(current.getInfo()) < 0) {
                    if (current == head) {
                        addNodeFirst(value); // Insert at the head
                    } else {
                        addNodeBeforeTo(current, value); // Insert before current
                    }
                    return;
                }
                current = current.getNext();
            }
            // If the new node is larger than all existing nodes, add it to the end
            addNodeLast(value);
        }
    }

    /**
     * Finds a node with the specified value.
     *
     * @param value the value to search for
     * @return the node containing the value, or null if not found
     */
    @SuppressWarnings("unlikely-arg-type")
    public Node<T> findNode(String code) {
        Node<T> current = head; // Inicia desde el nodo cabeza
        while (current != null) { // Mientras no llegue al final
            if (current.getInfo().equals(code)) { // Compara el valor
                return current; // Retorna el nodo encontrado
            }
            current = current.getNext(); // Mueve al siguiente nodo
        }
        return null; // Retorna null si no se encuentra
    }

    /**
     * Finds the info associated with the specified value.
     *
     * @param value the value to search for
     * @return the info of the node containing the value, or null if not found
     */
    public T findInfo(T value) {
        Node<T> current = head; // Inicia desde el nodo cabeza
        while (current != null) { // Mientras no llegue al final
            if (current.getInfo().equals(value)) { // Compara el valor
                return current.getInfo(); // Retorna la información del nodo encontrado
            }
            current = current.getNext(); // Mueve al siguiente nodo
        }
        return null; // Retorna null si no se encuentra
    }

    /**
     * Gets the elements of the linked list in a specified order.
     *
     * @param asc true to get elements in ascending order, false for descending
     *            order
     * @return a list of elements in the specified order
     */
    public List<T> getLinkedList(boolean asc) {
        List<T> elements = new ArrayList<>(); // Lista para almacenar los elementos
        Node<T> current;

        if (asc) {
            current = head; // Inicia desde la cabeza
            while (current != null) { // Mientras no llegue al final
                elements.add(current.getInfo()); // Agrega la información a la lista
                current = current.getNext(); // Mueve al siguiente nodo
            }
        } else {
            current = last; // Inicia desde el último nodo
            while (current != null) { // Mientras no llegue al principio
                elements.add(current.getInfo()); // Agrega la información a la lista
                current = current.getPrevious(); // Mueve al nodo anterior
            }
        }

        return elements; // Retorna la lista de elementos
    }

    /**
     * Deletes a specified node from the list.
     *
     * @param node the node to be deleted
     * @return the information of the deleted node
     */
    public T deleteNode(Node<T> node) {
        if (node == head && node == last) { // Only one node
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
        } while (current != head); // Loop until we return to head

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
        } while (current != head); // Loop until we return to head

        return null; // Position out of bounds
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
