package main.java.com.yourorg.lms.repository;


import main.java.com.yourorg.lms.model.course.Course;
import java.util.List;

/**
 * Repository abstraction for Course persistence.
 */
public interface CourseRepository {

    void save(Course course);

    void update(Course course); // Add this
    
    void delete(String id);
    
    List<Course> findAll();

    Course findById(String id);

    List<Course> findByInstructor(String instructorId);
}
