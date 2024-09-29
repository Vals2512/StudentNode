package model;

/**
 * Represents a student with attributes such as code, name, email, gender, and
 * career.
 */
public class Student extends Entity {

    private String name;
    private String lastname;
    private String email;
    private EGender gender;
    private String career;

    /**
     * Default constructor for the Student class.
     */
    public Student() {
    }

    /**
     * Constructs a new Student with the specified details.
     * 
     * @param id       the student's code
     * @param name     the student's name
     * @param email    the student's email
     * @param gender   the student's gender
     * @param career   the student's career
     * @param lastname the student's last name
     */

    public Student(String id, String name, String lastname, String email, EGender gender, String career) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.career = career;
    }

    /**
     * Gets the student's code.
     * 
     * @return the code of the student
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the student's code.
     * 
     * @param id the new code of the student
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the student's name.
     * 
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     * 
     * @param name the new name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the student's email.
     * 
     * @return the email of the student
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email.
     * 
     * @param email the new email of the student
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the student's gender.
     * 
     * @return the gender of the student
     */
    public EGender getGender() {
        return gender;
    }

    /**
     * Sets the student's gender.
     * 
     * @param gender the new gender of the student
     */
    public void setGender(EGender gender) {
        this.gender = gender;
    }

    /**
     * Gets the student's career.
     * 
     * @return the career of the student
     */
    public String getCareer() {
        return career;
    }

    /**
     * Sets the student's career.
     * 
     * @param career the new career of the student
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * Gets the student's lastname.
     * 
     * @return the lastname of the student
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Sets the student's lastname.
     * 
     * @param lastname the new lastname of the student
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Returns a string representation of the student object.
     * 
     * @return a string containing the student's details
     */
    @Override
    public String toString() {
        return "Student [ Code=" + id + ", name=" + name + ", Last name= " + lastname + ", email=" + email + ", gender="
                + gender + ", career="
                + career + "]";
    }

}
