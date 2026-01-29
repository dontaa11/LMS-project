package main.java.com.yourorg.lms.model.course;

public class StudentTask {
    private String studentId;
    private String id;
    private String title;
    private boolean completed;

    // This constructor now matches your dummy data!
    public StudentTask(String id, String studentId, String title, boolean completed) {
        this.id = id;
        this.studentId = studentId;
        this.title = title;
        this.completed = completed;
    }

    // Standard Getters
    public String getTitle() { return title; }
    public String getStudentId() { return studentId; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}