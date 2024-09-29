package controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Node;

class DoubleLinkedListTest {

    private DoubleLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty(), "List should be empty");
        list.addNodeFirst("Node1");
        assertFalse(list.isEmpty(), "List should not be empty");
    }

    @Test
    void testAddNodeFirst() {
        list.addNodeFirst("Node1");
        assertEquals("Node1", list.getFirst(), "First node should be Node1");
        list.addNodeFirst("Node2");
        assertEquals("Node2", list.getFirst(), "First node should now be Node2");
    }

    @Test
    void testAddNodeLast() {
        list.addNodeLast("Node1");
        assertEquals("Node1", list.getLast(), "Last node should be Node1");
        list.addNodeLast("Node2");
        assertEquals("Node2", list.getLast(), "Last node should now be Node2");
    }

    @Test
    void testAddNodeAfterTo() {
        list.addNodeFirst("Node1");
        list.addNodeLast("Node2");
        Node<String> node1 = list.findNode("Node1");
        list.addNodeAfterTo(node1, "Node3");
        assertEquals("Node3", node1.getNext().getInfo(), "Node3 should be added after Node1");
    }

    @Test
    void testAddNodeBeforeTo() {
        list.addNodeFirst("Node1");
        list.addNodeLast("Node2");
        Node<String> node2 = list.findNode("Node2");
        list.addNodeBeforeTo(node2, "Node3");
        assertEquals("Node3", node2.getPrevious().getInfo(), "Node3 should be added before Node2");
    }

    @Test
    void testAddNodeSorted() {
        list.addNodeSorted("Node3");
        list.addNodeSorted("Node1");
        list.addNodeSorted("Node2");
        assertEquals("Node1", list.getFirst(), "First node should be Node1 after sorting");
        assertEquals("Node3", list.getLast(), "Last node should be Node3 after sorting");
    }

    @Test
    void testFindNode() {
        list.addNodeFirst("Node1");
        Node<String> foundNode = list.findNode("Node1");
        assertNotNull(foundNode, "Node1 should be found");
        assertEquals("Node1", foundNode.getInfo(), "Found node should be Node1");
    }

    @Test
    void testFindInfo() {
        list.addNodeFirst("Node1");
        String info = list.findInfo("Node1");
        assertEquals("Node1", info, "Info should match Node1");
    }

    @Test
    void testGetLinkedList() {
        list.addNodeFirst("Node1");
        list.addNodeLast("Node2");
        list.addNodeLast("Node3");
        assertEquals(3, list.getLinkedList(true).size(), "Should return 3 elements in ascending order");
        assertEquals("Node3", list.getLinkedList(false).get(0), "First element in descending order should be Node3");
    }

    @Test
    void testDeleteNode() {
        list.addNodeFirst("Node1");
        Node<String> nodeToDelete = list.findNode("Node1");
        String deletedInfo = list.deleteNode(nodeToDelete);
        assertEquals("Node1", deletedInfo, "Deleted node's info should be Node1");
        assertNull(list.findNode("Node1"), "Node1 should be deleted");
    }

    @Test
    void testGetSize() {
        assertEquals(0, list.getSize(), "Size should be 0 initially");
        list.addNodeFirst("Node1");
        assertEquals(1, list.getSize(), "Size should be 1 after adding a node");
    }

    @Test
    void testGetObject() {
        list.addNodeFirst("Node1");
        assertEquals("Node1", list.getObject(0), "Should return Node1 at position 0");
        assertNull(list.getObject(1), "Should return null for out-of-bounds index");
    }

    @Test
    void testGetFirst() {
        assertNull(list.getFirst(), "Should return null for an empty list");
        list.addNodeFirst("Node1");
        assertEquals("Node1", list.getFirst(), "First node should be Node1");
    }

    @Test
    void testGetLast() {
        assertNull(list.getLast(), "Should return null for an empty list");
        list.addNodeFirst("Node1");
        assertEquals("Node1", list.getLast(), "Last node should be Node1");
    }
}
