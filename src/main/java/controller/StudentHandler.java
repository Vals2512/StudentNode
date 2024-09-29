package controller;

import model.Student;
import model.Node;
import java.util.List;

/**
 * Handler class for managing CRUD operations on a list of students using a
 * doubly linked list.
 */
public class StudentHandler {
    private DoubleLinkedList<Student> studentList;

    /**
     * Initializes a new StudentHandler with an empty list.
     */
    public StudentHandler() {
        studentList = new DoubleLinkedList<>();
    }

    /**
     * Creates: Adds a new student in a sorted order by last name.
     *
     * @param student the student to add
     * @return true if the student was successfully added
     * @throws IllegalArgumentException if the student is null, missing required
     *                                  fields, or if a student with the same code
     *                                  already exists
     */
    public boolean addStudent(Student student) {
        if (student == null || student.getLastName() == null || student.getCode() == null) {
            throw new IllegalArgumentException("Student or required fields are null.");
        }

        // Prevent duplicates by code
        if (findStudentByCode(student.getCode()) != null) {
            throw new IllegalArgumentException("A student with the same code already exists.");
        }

        studentList.addNodeSorted(student);
        return true;
    }

    /**
     * Adds a student at the beginning of the list.
     * 
     * @param student the student to add
     */
    public void addStudentFirst(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        studentList.addNodeFirst(student);
    }

    /**
     * Adds a student at the end of the list.
     * 
     * @param student the student to add
     */
    public void addStudentLast(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        studentList.addNodeLast(student);
    }

    /**
     * Reads: Finds a student by their unique code.
     *
     * @param code the unique code of the student to search for
     * @return the student if found, or null if not found
     * @throws IllegalArgumentException if the code is null or empty
     */
    public Student findStudentByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("The student code cannot be null or empty.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        return (foundNode != null) ? foundNode.getInfo() : null; 
    }

    /**
     * Updates: Updates the information of an existing student.
     *
     * @param code           the unique code of the student to update
     * @param updatedStudent the student object containing updated information
     * @return true if the student was successfully updated
     * @throws IllegalArgumentException if the code is null, empty, or if the
     *                                  updatedStudent is null
     * @throws IllegalStateException    if the student with the given code is not
     *                                  found
     */
    public boolean updateStudent(String code, Student updatedStudent) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("The student code cannot be null or empty.");
        }
        if (updatedStudent == null) {
            throw new IllegalArgumentException("Updated student cannot be null.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            foundNode.setInfo(updatedStudent);
            return true;
        }
        throw new IllegalStateException("Student with the given code not found.");
    }

    /**
     * Deletes: Removes a student by their unique code.
     *
     * @param code the unique code of the student to delete
     * @return true if the student was successfully deleted
     * @throws IllegalArgumentException if the code is null or empty
     * @throws IllegalStateException    if the student with the given code is not
     *                                  found
     */
    public boolean deleteStudent(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("The student code cannot be null or empty.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            studentList.deleteNode(foundNode);
            return true;
        }
        throw new IllegalStateException("Student with the given code not found.");
    }

    /**
     * Retrieves a list of all students in the specified order.
     *
     * @param ascending true for ascending order, false for descending order
     * @return a list of all students in the specified order
     */
    public List<Student> listAllStudents(boolean ascending) {
        return studentList.getLinkedList(ascending);
    }

    /**
     * Checks if the student list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    /**
     * Retrieves the number of students in the list.
     *
     * @return the total number of students
     */
    public int getNumberOfStudents() {
        return studentList.getSize();
    }

    /**
     * Finds the first student in the list.
     *
     * @return the first student, or null if the list is empty
     */
    public Student getFirstStudent() {
        return studentList.getFirst();
    }

    /**
     * Finds the last student in the list.
     *
     * @return the last student, or null if the list is empty
     */
    public Student getLastStudent() {
        return studentList.getLast();
    }

    /**
     * Adds a student before a specific student based on the unique code.
     * 
     * @param code    the code of the existing student
     * @param student the new student to add before
     */
    public void addStudentBefore(String code, Student student) {
        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            studentList.addNodeBeforeTo(foundNode, student);
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

    /**
     * Adds a student after a specific student based on the unique code.
     * 
     * @param code    the code of the existing student
     * @param student the new student to add after
     */
    public void addStudentAfter(String code, Student student) {
        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            studentList.addNodeAfterTo(foundNode, student);
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

    /**
     * Retrieves a student by position in the list.
     * 
     * @param position the position in the list (0-based index)
     * @return the student at the specified position, or null if out of bounds
     */
    public Student getStudentByPosition(int position) {
        return studentList.getObject(position);
    }
}
