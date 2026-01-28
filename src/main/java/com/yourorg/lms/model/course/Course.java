package main.java.com.yourorg.lms.model.course;

import java.io.Serializable;
import java.util.Objects;

/**
 * Course domain model.
 *
 * SOLID:
 * - SRP: Holds course state only.
 * - Encapsulation: Immutable fields.
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final String title;
    private final String description;
    private final String instructorId;

    public Course(String id, String title, String description, String instructorId) {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("Course ID required");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Course title required");
        if (instructorId == null || instructorId.isBlank())
            throw new IllegalArgumentException("Instructor ID required");

        this.id = id;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getInstructorId() { return instructorId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        return id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
