package main.java.com.yourorg.lms.repository.impl;


import main.java.com.yourorg.lms.model.course.Course;
import main.java.com.yourorg.lms.repository.CourseRepository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileCourseRepository implements CourseRepository {

    private static final Path DATA_DIR = Paths.get("data");
    private static final Path COURSE_FILE = DATA_DIR.resolve("courses.txt");
    private static final String DELIMITER = "|";

    private static FileCourseRepository instance;
    private final List<Course> cache = new ArrayList<>();

    // --- Singleton ---
    private FileCourseRepository() {
        initStorage();
        refreshCache();
    }

    public static synchronized FileCourseRepository getInstance() {
        if (instance == null) {
            instance = new FileCourseRepository();
        }
        return instance;
    }

    // --- Initialization (Defensive) ---
    private void initStorage() {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
            if (!Files.exists(COURSE_FILE)) {
                Files.createFile(COURSE_FILE);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to initialize course storage");
            e.printStackTrace();
        }
    }

    // --- CRUD Operations ---

    @Override
    public void save(Course course) {
        cache.add(course);

        try (BufferedWriter writer = Files.newBufferedWriter(
                COURSE_FILE,
                StandardOpenOption.APPEND,
                StandardOpenOption.CREATE)) {

            writer.write(String.join(DELIMITER,
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    course.getInstructorId()
            ));
            writer.newLine();

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to save course");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course course) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getId().equals(course.getId())) {
                cache.set(i, course);
                rewriteFile();
                return;
            }
        }
    }

    @Override
    public void delete(String id) {
        cache.removeIf(c -> c.getId().equals(id));
        rewriteFile();
    }

   @Override
    public Course findById(String id) {
        // Search the cache first for speed
        return cache.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(cache); // Used for the Course Catalog tab
    } 

    @Override
    public List<Course> findByInstructor(String instructorId) {
        List<Course> result = new ArrayList<>();
        for (Course c : cache) {
            if (c.getInstructorId().equals(instructorId)) {
                result.add(c);
            }
        }
        return result;
    }

    // --- Cache Management ---

    public void refreshCache() {
        cache.clear();

        try (BufferedReader reader = Files.newBufferedReader(COURSE_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] p = line.split("\\|", -1);

                // Defensive check (VERY IMPORTANT)
                if (p.length != 4) {
                    System.err.println("[WARN] Skipping malformed course line: " + line);
                    continue;
                }

                Course course = new Course(
                        p[0],
                        p[1],
                        p[2],
                        p[3]
                );
                cache.add(course);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to load courses");
            e.printStackTrace();
        }
    }

    // --- Internal Rewrite Logic (SRP-compliant) ---
    private void rewriteFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(
                COURSE_FILE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE)) {

            for (Course c : cache) {
                writer.write(String.join(DELIMITER,
                        c.getId(),
                        c.getTitle(),
                        c.getDescription(),
                        c.getInstructorId()
                ));
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to rewrite course file");
            e.printStackTrace();
        }
    }
}
