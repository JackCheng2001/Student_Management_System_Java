import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cheng Erxi, Jiayi Wang
 */
public class StudentTest{

    private Student student;
    private Course course1;
    private Course course2;
    private List<Course> allCourses;
    private Map<String, String> grades;
    @BeforeEach
    /**
     * Setup method that initializes common objects before each test.
     */
    void setUp(){
        //initialize a student instance and create sample courses
        Map<String, String> grades = new HashMap<>();
        grades.put("CIT590", "A");
        grades.put("ENM503", "B");

        student = new Student("666","Jess Wang","jess1102","12345", grades);
        course1 = new Course("CIS590","Java","Dr.Who", "Monday","9:00 AM","11:00 AM",25);
        course2 = new Course("ENM503","Statistics","Dr.Woo","Friday","2:30 PM", "4:30 PM", 50);
    }
    /**
     * Test the enrollInCourse method of the Student class.
     */
    @Test
    public void testEnrollInCourse(){
        student.enrollInCourse(course1);
        student.enrollInCourse(course2);
        // Assert that the student is enrolled in both courses

        assertEquals(2, student.getEnrolledCourses().size());
        assertTrue(student.getEnrolledCourses().containsValue(course1));
        assertTrue(student.getEnrolledCourses().containsValue(course2));
    }
    /**
     * Test dropping an enrolled course.
     */
    @Test
    public void testDropCourse(){

        //drop enrolled classes
        student.enrollInCourse(course1);
        student.enrollInCourse(course2);

        student.dropCourse("CIT590");
        // Assert that only one course remains

        assertEquals(2,student.getEnrolledCourses().size());
        assertTrue(student.getEnrolledCourses().containsValue(course2));

    }
    /**
     * Test dropping a course that the student is not enrolled in.
     */
    @Test
    public void testDropNonenrolledCourse(){
        //2.drop nonenrolled classes
        student.enrollInCourse(course1);

        student.dropCourse("ENM503");
        // Assert that the enrolled course remains unchanged

        assertEquals(1, student.getEnrolledCourses().size());
        assertTrue(student.getEnrolledCourses().containsValue(course1));
    }
    /**
     * Test retrieving grades.
     */
    @Test
    public void testGetGrades(){
        Map<String, String>grades = student.getGrades();
        // Assert that the grades match the expected values

        assertEquals(2, grades.size());
        assertEquals("A", grades.get("CIT590"));
        assertEquals("B",grades.get("ENM503"));
    }
    /**
     * Test setting grades for an enrolled course.
     */
    @Test
    public void testSetGradeForCourse() {

        //1.test set grade for enrolled course
        student.enrollInCourse(course1);

        student.setGradeForCourse("CIT590", "A");
        // Assert that the grade is set correctly

        assertEquals("A", student.getGrades().get("CIT590"));
    }
    /**
     * Test setting grades for a course that doesn't exist in the grades map.
     */
    @Test
    public void testSetGradeForNonenrolledCourse(){

        //2.test set grade for nonenrolled course
        student.setGradeForCourse("CIT500","B");
        assertNull(student.getGrades().get("CIT500"));

    }

    @Test
    public void testSetGradeForNonexistingCourse(){

        //3.test set grade for course not in grades map
        student.enrollInCourse(course1);
        student.setGradeForCourse("CIT500","A");
        assertNull(student.getGrades().get("CIT500"));
    }

}
