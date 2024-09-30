package controller;

import model.Student;
import model.Node;
import java.util.List;

/**
 * Handler class for managing CRUD (Create, Read, Update, Delete) operations on
 * a list of students using a doubly linked list.
 */
public class StudentHandler {
    private static StudentHandler instance;
    private DoubleLinkedList<Student> studentList;

    /**
     * Initializes a new StudentHandler with an empty list.
     */
    public StudentHandler() {
        studentList = new DoubleLinkedList<>();
    }

    /**
     * Returns the singleton instance of the StudentHandler class.
     * Uses double-checked locking to ensure thread safety.
     *
     * @return the single instance of StudentHandler
     */
    public static StudentHandler getInstance() {
        if (instance == null) {
            synchronized (StudentHandler.class) {
                if (instance == null) {
                    instance = new StudentHandler();
                }
            }
        }
        return instance;
    }

    /**
     * Adds a student to the list in a sorted order.
     *
     * @param student the student to be added
     * @return true if the student was added successfully
     * @throws IllegalArgumentException if the student or required fields are null,
     *                                  or if a student with the same ID already
     *                                  exists
     */
    public boolean addStudent(Student student) {
        if (student == null || student.getLastName() == null || student.getId() == null) {
            throw new IllegalArgumentException("Student or required fields are null.");
        }

        if (findStudentByCode(student.getId()) != null) {
            throw new IllegalArgumentException("A student with the same code already exists.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        studentList.addNodeSorted(student);
        return true;
    }

    /**
     * Adds a student to the beginning of the list.
     *
     * @param student the student to be added
     * @return true after successfully adding the student at the beginning
     * @throws IllegalArgumentException if the student is null or the ID is already
     *                                  in use
     */
    public boolean addStudentFirst(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        studentList.addNodeFirst(student);
        return true;
    }

    /**
     * Adds a student to the end of the list.
     *
     * @param student the student to be added
     * @return true after successfully adding the student at the end
     * @throws IllegalArgumentException if the student is null or the ID is already
     *                                  in use
     */
    public boolean addStudentLast(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        studentList.addNodeLast(student);
        return true;
    }

    /**
     * Finds a student by their code.
     *
     * @param code the student's code
     * @return the student with the given code, or null if not found
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
     * Updates a student's information.
     *
     * @param code           the code of the student to update
     * @param updatedStudent the updated student information
     * @return true if the student was successfully updated
     * @throws IllegalArgumentException if the code is null, empty, or the
     *                                  updatedStudent is null
     * @throws IllegalStateException    if a student with the given code is not
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

            if (!foundNode.getInfo().getId().equals(updatedStudent.getId())) {
                if (studentExists(updatedStudent.getId())) {
                    throw new IllegalArgumentException("The ID is already in use.");
                }
            }
            foundNode.setInfo(updatedStudent);
            return true;
        }
        throw new IllegalStateException("Student with the given code not found.");
    }

    /**
     * Deletes a student by their code.
     *
     * @param code the student's code
     * @return true if the student was successfully deleted
     * @throws IllegalArgumentException if the code is null or empty
     * @throws IllegalStateException    if a student with the given code is not
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
     * Lists all students in either ascending or descending order.
     *
     * @param ascending whether to list in ascending order
     * @return a list of all students
     */
    public List<Student> listAllStudents(boolean ascending) {
        return studentList.getLinkedList(ascending);
    }

    /**
     * Checks if the list of students is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    /**
     * Gets the total number of students.
     *
     * @return the number of students
     */
    public int getNumberOfStudents() {
        return studentList.getSize();
    }

    /**
     * Retrieves the first student in the list.
     *
     * @return the first student, or null if the list is empty
     */
    public Student getFirstStudent() {
        return studentList.isEmpty() ? null : studentList.getFirst();
    }

    /**
     * Retrieves the last student in the list.
     *
     * @return the last student, or null if the list is empty
     */
    public Student getLastStudent() {
        return studentList.isEmpty() ? null : studentList.getLast();
    }

    /**
     * Adds a student before another student identified by a code.
     *
     * @param code    the reference student's code
     * @param student the student to add
     * @return true if the student was added successfully
     * @throws IllegalArgumentException if the student is null or the ID is already
     *                                  in use
     */
    public boolean addStudentBefore(String code, Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            studentList.addNodeBeforeTo(foundNode, student);
            return true;
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

    /**
     * Adds a student after another student identified by a code.
     *
     * @param code    the reference student's code
     * @param student the student to add
     * @return true if the student was added successfully
     * @throws IllegalArgumentException if the student is null or the ID is already
     *                                  in use
     */
    public boolean addStudentAfter(String code, Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        if (foundNode != null) {
            studentList.addNodeAfterTo(foundNode, student);
            return true;
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

    /**
     * Retrieves a student by their position in the list.
     *
     * @param position the position of the student (starting from 0)
     * @return the student at the specified position
     * @throws IllegalArgumentException if the position is invalid
     */
    public Student getStudentByPosition(int position) {
        if (position < 0 || position >= getNumberOfStudents()) {
            throw new IllegalArgumentException("Position invalid, it starts from 0.");
        }
        return studentList.getObject(position);
    }

    /**
     * Checks if a student exists based on their code.
     *
     * @param code the student's code
     * @return true if the student exists, false otherwise
     */
    public boolean studentExists(String code) {
        return findStudentByCode(code) != null;
    }
}
