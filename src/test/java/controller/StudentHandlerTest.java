package controller;

import model.EGender;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

/**
 * Test class for CRUD operations in the StudentHandler class.
 */
public class StudentHandlerTest {
    private StudentHandler handler;

    /**
     * Initializes the StudentHandler instance before each test.
     */
    @BeforeEach
    public void setUp() {
        handler = StudentHandler.getInstance();
    }

    /**
     * Resets the StudentHandler instance after each test to ensure a clean state.
     */
    @AfterEach
    public void down() {
        handler.restartInstance();
    }

    /**
     * Tests adding a student and verifies that the student can be retrieved.
     */
    @Test
    public void testAddStudent() {
        assertTrue(handler.addStudent("001", "Camila", "Gonzalez", "camila.gonzalez", EGender.FEMALE, "systems"));

        Student retrievedStudent = handler.findStudentByCode("001");
        assertNotNull(retrievedStudent, "Student should be found");
        assertEquals("Camila", retrievedStudent.getName());
        assertEquals("Gonzalez", retrievedStudent.getLastName());
    }

    /**
     * Tests adding students and verifies they are stored in the correct sorted
     * order by last name.
     */
    @Test
    public void testAddStudentSortedOrder() {
        handler.addStudent("001", "Ana", "Martinez", "camila.gonzalez", EGender.FEMALE, "systems");
        handler.addStudent("003", "Brenda", "Garcia", "camila.gonzalez", EGender.FEMALE, "systems");
        handler.addStudent("002", "Carlos", "Alvarez", "camila.gonzalez", EGender.MALE, "systems");
        handler.addStudent("006", "Carlos", "Ramirez", "camila.gonzalez", EGender.MALE, "systems");
        handler.addStudent("004", "Carlos", "Vega", "camila.gonzalez", EGender.MALE, "systems");
        handler.addStudent("005", "Carlos", "Alvarez", "camila.gonzalez", EGender.MALE, "systems");

        assertEquals("Martinez", handler.listAllStudents(true).get(0).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(true).get(1).getLastName());
        assertEquals("Garcia", handler.listAllStudents(true).get(2).getLastName());
        assertEquals("Vega", handler.listAllStudents(true).get(3).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(true).get(4).getLastName());
        assertEquals("Ramirez", handler.listAllStudents(true).get(5).getLastName());

        assertEquals("Vega", handler.listAllStudents(false).get(2).getLastName());
        assertEquals("Ramirez", handler.listAllStudents(false).get(0).getLastName());
        assertEquals("Martinez", handler.listAllStudents(false).get(5).getLastName());
        assertEquals("Garcia", handler.listAllStudents(false).get(3).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(false).get(4).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(false).get(1).getLastName());
    }

    /**
     * Tests updating a student's information and ensures the changes are reflected.
     */
    @Test
    public void testUpdateStudent() {
        handler.addStudent("001", "Maria", "Perez", "camila.gonzalez", EGender.FEMALE, "systems");
        assertTrue(handler.updateStudent("001", "Maria Camila", "Perez", "camila.gonzalez", EGender.FEMALE, "systems"));

        Student retrievedStudent = handler.findStudentByCode("001");
        assertEquals("Maria Camila", retrievedStudent.getName());
    }

    /**
     * Tests deleting a student and verifies that the student is no longer found.
     */
    @Test
    public void testDeleteStudent() {
        handler.addStudent("001", "Diego", "Salazar", "camila.gonzalez", EGender.FEMALE, "systems");
        assertTrue(handler.deleteStudent("001"));

        assertNull(handler.findStudentByCode("001"));
    }

    /**
     * Tests if the student list is empty initially and after adding a student.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(handler.isEmpty(), "List should be initially empty");
        handler.addStudent("001", "Laura", "Rivera", "camila.gonzalez", EGender.FEMALE, "systems");
        assertFalse(handler.isEmpty());
    }

    /**
     * Tests the number of students in the handler.
     */
    @Test
    public void testGetNumberOfStudents() {
        assertEquals(0, handler.getNumberOfStudents(), "Initially, there should be no students");

        handler.addStudent("001", "Julian", "Lopez", "camila.gonzalez", EGender.FEMALE, "systems");
        assertEquals(1, handler.getNumberOfStudents(), "There should be one student in the list");

        handler.addStudent("002", "Andres", "Hernandez", "camila.gonzalez", EGender.FEMALE, "systems");
        assertEquals(2, handler.getNumberOfStudents(), "There should be two students in the list");
    }

    /**
     * Tests retrieval of the first and last students in the list.
     */
    @Test
    public void testGetFirstAndLastStudent() {
        handler.addStudent("001", "Carlos", "Gomez", "camila.gonzalez", EGender.FEMALE, "systems");
        handler.addStudent("002", "Luis", "Mendoza", "camila.gonzalez", EGender.FEMALE, "systems");

        assertEquals("Gomez", handler.getFirstStudent().getLastName(), "The first student should be Carlos Gomez");
        assertEquals("Mendoza", handler.getLastStudent().getLastName(), "The last student should be Luis Mendoza");
    }

    /**
     * Tests adding a student before and after another student in the list.
     */
    @Test
    public void testAddStudentBeforeAndAfter() {
        handler.addStudent("001", "Lucia", "Diaz", "camila.gonzalez", EGender.FEMALE, "systems");
        handler.addStudent("002", "Sofia", "Jimenez", "camila.gonzalez", EGender.FEMALE, "systems");

        handler.addStudentBefore("002", "003", "Alejandro", "Hernandez", "camila.gonzalez", EGender.FEMALE, "systems");
        assertEquals("Hernandez", handler.listAllStudents(true).get(1).getLastName(),
                "Alejandro Hernandez should be before Sofia Jimenez");

        handler.addStudentAfter("001", "004", "Natalia", "Romero", "camila.gonzalez", EGender.FEMALE, "systems");
        assertEquals("Romero", handler.listAllStudents(true).get(1).getLastName());
        assertEquals("Hernandez", handler.listAllStudents(true).get(2).getLastName());
        assertEquals("Jimenez", handler.listAllStudents(true).get(3).getLastName());
        assertEquals("Diaz", handler.listAllStudents(true).get(0).getLastName());
    }

    /**
     * Tests retrieving a student by their position in the list.
     */
    @Test
    public void testGetStudentByPosition() {
        handler.addStudent("001", "Andres", "Villanueva", "camila.gonzalez", EGender.FEMALE, "systems");
        handler.addStudent("002", "Camila", "Ortiz", "camila.gonzalez", EGender.FEMALE, "systems");

        assertEquals("Ortiz", handler.getStudentByPosition(1).getLastName(), "Camila Ortiz should be at position 1");
    }
}