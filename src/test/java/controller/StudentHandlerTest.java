package controller;

import model.EGender;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StudentHandler CRUD operations.
 */
public class StudentHandlerTest {
    private StudentHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new StudentHandler();
    }

    @Test
    public void testAddStudent() {
        Student student1 = new Student("001", "Camila", "Gonzalez", "camila.gonzalez", EGender.FEMALE, "systems");
        assertTrue(handler.addStudent(student1));

        Student retrievedStudent = handler.findStudentByCode("001");
        assertNotNull(retrievedStudent, "Student should be found");
        assertEquals("Camila", retrievedStudent.getName());
        assertEquals("Gonzalez", retrievedStudent.getLastName());
    }

    @Test
    public void testAddStudentSortedOrder() {
        handler.addStudent(new Student("001", "Ana", "Martinez", "camila.gonzalez", EGender.FEMALE, "systems"));
        handler.addStudent(new Student("003", "Brenda", "Garcia", "camila.gonzalez", EGender.FEMALE, "systems"));
        handler.addStudent(new Student("002", "Carlos", "Alvarez", "camila.gonzalez", EGender.MALE, "systems"));
        handler.addStudent(new Student("006", "Carlos", "Ramirez", "camila.gonzalez", EGender.MALE, "systems"));
        handler.addStudent(new Student("004", "Carlos", "Vega", "camila.gonzalez", EGender.MALE, "systems"));
        handler.addStudent(new Student("005", "Carlos", "Alvarez", "camila.gonzalez", EGender.MALE, "systems"));

        assertEquals("Martinez", handler.listAllStudents(true).get(0).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(true).get(1).getLastName());
        assertEquals("Garcia", handler.listAllStudents(true).get(2).getLastName());
        assertEquals("Vega", handler.listAllStudents(true).get(3).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(true).get(4).getLastName());
        assertEquals("Ramirez", handler.listAllStudents(true).get(5).getLastName());

        assertEquals("Ramirez", handler.listAllStudents(false).get(0).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(false).get(1).getLastName());
        assertEquals("Vega", handler.listAllStudents(false).get(2).getLastName());
        assertEquals("Garcia", handler.listAllStudents(false).get(3).getLastName());
        assertEquals("Alvarez", handler.listAllStudents(false).get(4).getLastName());
        assertEquals("Martinez", handler.listAllStudents(false).get(5).getLastName());
    }

    @Test
    public void testUpdateStudent() {
        handler.addStudent(new Student("001", "Maria", "Perez", "camila.gonzalez", EGender.FEMALE, "systems"));
        Student updatedStudent = new Student("001", "Maria Camila", "Perez", "camila.gonzalez", EGender.FEMALE,
                "systems");
        assertTrue(handler.updateStudent("001", updatedStudent));

        Student retrievedStudent = handler.findStudentByCode("001");
        assertEquals("Maria Camila", retrievedStudent.getName());
    }

    @Test
    public void testDeleteStudent() {
        handler.addStudent(new Student("001", "Diego", "Salazar", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertTrue(handler.deleteStudent("001"));

        assertNull(handler.findStudentByCode("001"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(handler.isEmpty(), "List should be initially empty");
        handler.addStudent(new Student("001", "Laura", "Rivera", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertFalse(handler.isEmpty());
    }

    @Test
    public void testGetNumberOfStudents() {
        assertEquals(0, handler.getNumberOfStudents(), "Initially, there should be no students");

        handler.addStudent(new Student("001", "Julian", "Lopez", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertEquals(1, handler.getNumberOfStudents(), "There should be one student in the list");

        handler.addStudent(new Student("002", "Andres", "Hernandez", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertEquals(2, handler.getNumberOfStudents(), "There should be two students in the list");
    }

    @Test
    public void testGetFirstAndLastStudent() {
        handler.addStudent(new Student("001", "Carlos", "Gomez", "camila.gonzalez", EGender.FEMALE, "systems"));
        handler.addStudent(new Student("002", "Luis", "Mendoza", "camila.gonzalez", EGender.FEMALE, "systems"));

        assertEquals("Gomez", handler.getFirstStudent().getLastName(), "The first student should be Carlos Gomez");
        assertEquals("Mendoza", handler.getLastStudent().getLastName(), "The last student should be Luis Mendoza");
    }

    @Test
    public void testAddStudentBeforeAndAfter() {
        handler.addStudent(new Student("001", "Lucia", "Diaz", "camila.gonzalez", EGender.FEMALE, "systems"));
        handler.addStudent(new Student("002", "Sofia", "Jimenez", "camila.gonzalez", EGender.FEMALE, "systems"));

        handler.addStudentBefore("002",
                new Student("003", "Alejandro", "Hernandez", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertEquals("Hernandez", handler.listAllStudents(true).get(1).getLastName(),
                "Alejandro Hernandez should be before Sofia Jimenez");

        handler.addStudentAfter("001",
                new Student("004", "Natalia", "Romero", "camila.gonzalez", EGender.FEMALE, "systems"));
        assertEquals("Romero", handler.listAllStudents(true).get(1).getLastName());
        assertEquals("Hernandez", handler.listAllStudents(true).get(2).getLastName());
        assertEquals("Jimenez", handler.listAllStudents(true).get(3).getLastName());
        assertEquals("Diaz", handler.listAllStudents(true).get(0).getLastName());
    }

    @Test
    public void testGetStudentByPosition() {
        handler.addStudent(new Student("001", "Andres", "Villanueva", "camila.gonzalez", EGender.FEMALE, "systems"));
        handler.addStudent(new Student("002", "Camila", "Ortiz", "camila.gonzalez", EGender.FEMALE, "systems"));

        assertEquals("Ortiz", handler.getStudentByPosition(1).getLastName(), "Camila Ortiz should be at position 1");
    }
}
