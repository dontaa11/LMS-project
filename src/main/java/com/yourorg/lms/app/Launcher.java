package main.java.com.yourorg.lms.app;

import main.java.com.yourorg.lms.util.SecurityUtil;

public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
        //Temporary code to generate one hashed admin
    String rawPassword = "admin123";
    String hashedPassword = SecurityUtil.hash(rawPassword); // Use your actual hashing utility name

    System.out.println("Copy this into users.txt:");
    System.out.println("ADM_001,System Admin,admin@lms.com," + hashedPassword + ",ADMIN");
    }
}