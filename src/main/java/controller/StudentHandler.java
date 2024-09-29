package controller;

import model.Student;
import model.Node;
import java.util.List;

/**
 * Handler class for managing CRUD operations on a list of students using a
 * doubly linked list.
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

    public boolean addStudentFirst(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        studentList.addNodeFirst(student);
        return true; // Return true after successfully adding the student at the beginning
    }

    public boolean addStudentLast(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (studentExists(student.getId())) {
            throw new IllegalArgumentException("The ID is already in use.");
        }

        studentList.addNodeLast(student);
        return true; // Return true after successfully adding the student at the end
    }

    public Student findStudentByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("The student code cannot be null or empty.");
        }

        Node<Student> foundNode = studentList.findNode(code);
        return (foundNode != null) ? foundNode.getInfo() : null;
    }

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

    public List<Student> listAllStudents(boolean ascending) {
        return studentList.getLinkedList(ascending);
    }

    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    public int getNumberOfStudents() {
        return studentList.getSize();
    }

    public Student getFirstStudent() {
        return studentList.isEmpty() ? null : studentList.getFirst();
    }

    public Student getLastStudent() {
        return studentList.isEmpty() ? null : studentList.getLast();
    }

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
            return true; // Return true after successfully adding the student before the found node
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

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
            return true; // Return true after successfully adding the student after the found node
        } else {
            throw new IllegalArgumentException("Student with the given code not found.");
        }
    }

    public Student getStudentByPosition(int position) {
        if (position < 0 || position >= getNumberOfStudents()) {
            throw new IllegalArgumentException("Position invalid, it tarts from 0.");
        }
        return studentList.getObject(position);
    }

    public boolean studentExists(String code) {
        return findStudentByCode(code) != null;
    }
}