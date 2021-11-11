package Entity;
/**
 * Entity Class for Customer
 * @author Ju Khang
 * @version 1.0
 * @since 2021-11-11
 */
import java.io.Serializable;

/**
 * Staff object for the staff members of the restaurant
 * Every staff object has an id, gender, name and job title
 * @author Ju Khang, Zaki
 * @version 1.0
 * @since 2021-11-11
 */
public class staff implements Serializable {
    private int employeeId;
    private char gender;
    private String name;
    private String jobTitle;

    /**
     * Constructor for Staff Object
     * @param employeeId unique id for the staff
     * @param name staff name
     * @param gender staff gender
     * @param job staff position
     */
    public staff(int employeeId, String name, char  gender, String job)
    {
        this.employeeId = employeeId;
        this.name = name;
        this.gender = gender;
        this.jobTitle = job;
    }

    /**
     * Gets the employeeId of the current employee
     * @return current employee id
     */
    public int getEmployeeId() {
        return this.employeeId;
    }

    /**
     * Sets the id for the current employee
     * @param employeeId new employee id for the current employee
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the gender of the current employee
     * @return current employee's gender
     */
    public char getGender() {
        return this.gender;
    }

    /**
     * Sets the gender for the current employee
     * @param gender new gender for the current employee
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * Gets the name of the current employee
     * @return name of the current employee
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name for the current employee
     * @param name new name for the current employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the job title for the current employee
     * @return job title for the current employee
     */
    public String getJobTitle() {
        return this.jobTitle;
    }

    /**
     * Sets the job title for the current employee
     * @param jobTitle new job title for the current employee
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
