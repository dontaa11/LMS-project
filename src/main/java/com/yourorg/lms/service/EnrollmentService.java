package main.java.com.yourorg.lms.service;

import java.util.ArrayList;
import java.util.List;

import main.java.com.yourorg.lms.model.course.Course;
import main.java.com.yourorg.lms.model.user.Student;
import main.java.com.yourorg.lms.model.user.User;
import main.java.com.yourorg.lms.repository.CourseRepository;
import main.java.com.yourorg.lms.repository.EnrollmentRepository;
import main.java.com.yourorg.lms.repository.impl.FileCourseRepository;
import main.java.com.yourorg.lms.repository.impl.FileEnrollmentRepository;
import main.java.com.yourorg.lms.util.SessionManager;

/**
 * EnrollmentService
 *
 * SOLID:
 * - SRP: Handles enrollment business rules only.
 * - DIP: Depends on repository interfaces, not implementations.
 * - OCP: New enrollment rules can be added without changing UI code.
 *
 * Security:
 * - Enforces role-based access (only STUDENT can enroll).
 */
public final class EnrollmentService {

    private static EnrollmentService instance;

    private final EnrollmentRepository enrollmentRepo;
    private final CourseRepository courseRepo;
    private final SessionManager sessionManager;

    private EnrollmentService() {
        this.enrollmentRepo = FileEnrollmentRepository.getInstance();
        this.courseRepo = FileCourseRepository.getInstance();
        this.sessionManager = SessionManager.getInstance();
    }

    public static synchronized EnrollmentService getInstance() {
        if (instance == null) {
            instance = new EnrollmentService();
        }
        return instance;
    }

    /**
     * Enrolls the currently logged-in student into a course.
     *
     * @param courseId the course ID
     * @throws IllegalStateException if user is not a student or already enrolled
     */
    public void enrollCurrentStudent(String courseId) {
        if (!sessionManager.isLoggedIn()) {
            throw new IllegalStateException("User must be logged in to enroll");
        }

        User currentUser = SessionManager.getCurrentUser();

        // Role-based security check
        if (!(currentUser instanceof Student student)) {
            throw new IllegalStateException("Only students can enroll in courses.");
        }

        if (enrollmentRepo.isEnrolled(student.getId(), courseId)) {
            throw new IllegalStateException("Student is already enrolled in this course.");
        }

        enrollmentRepo.enroll(student.getId(), courseId);
    }

    /**
     * Returns all courses a student is enrolled in.
     */
    public List<Course> getStudentEnrolledCourses(String studentId) {
        List<Course> result = new ArrayList<>();

        for (String courseId : enrollmentRepo.getCourseIdsByStudent(studentId)) {
            Course course = courseRepo.findById(courseId);
            if (course != null) {
                result.add(course);
            }
        }
        return result;
    }
}
