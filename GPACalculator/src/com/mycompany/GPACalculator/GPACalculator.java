/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.GPACalculator;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *.@author Banso Oluwaseun Emmanuel
 */

import java.util.ArrayList;
import java.util.Scanner;

public class GPACalculator {

    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();
        
        System.out.println("Enter your Name");
        String StudentName = input.nextLine();

        while (true) {
          
            System.out.println("Enter course information (or type 'exit' to finish):");
            System.out.print("Course name & code: ");
            String courseNameAndCode = input.nextLine();

            if (courseNameAndCode.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Course unit: ");
            int courseUnit = input.nextInt();
            input.nextLine(); // consume the newline character

            System.out.print("Score: ");
            int score = input.nextInt();
            input.nextLine(); // consume the newline character

            // Calculate grade and grade unit
            String grade = calculateGrade(score);
            int gradeUnit = calculateGradeUnit(grade);

            // Create a course object and add it to the list
            Course course = new Course(courseNameAndCode, courseUnit, grade, gradeUnit);
            courses.add(course);
        }

        // Display the result in a tabular form
       System.out.println("\nStudent Name: " + StudentName);
        displayResults(courses);

        // Calculate and display GPA
        double gpa = calculateGPA(courses);
        System.out.printf("Your GPA is = %.2f to 2 decimal places.\n", gpa);
    }

    private static String calculateGrade(int score) {
        if (score >= 70) {
            return "A";
        } else if (score >= 60) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else if (score >= 45) {
            return "D";
        } else if (score >= 40) {
            return "E";
        } else {
            return "F";
        }
    }

    private static int calculateGradeUnit(String grade) {
        switch (grade) {
            case "A":
                return 5;
            case "B":
                return 4;
            case "C":
                return 3;
            case "D":
                return 2;
            case "E":
                return 1;
            default:
                return 0;
        }
    }

    private static void displayResults(ArrayList<Course> courses) {
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE  |            COURSE UNIT  |          GRADE |      GRADE-UNIT |         |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");

        for (Course course : courses) {
            System.out.printf("| %-25s  |%-23d| %-11s|  %-18d |\n", course.getNameAndCode(), course.getCourseUnit(), course.getGrade(), course.getGradeUnit());
        }

        System.out.println("|---------------------------------------------------------------------------------------|");
    }

    private static double calculateGPA(ArrayList<Course> courses) {
        double totalQualityPoints = 0.0;
        int totalGradeUnits = 0;

        for (Course course : courses) {
            totalQualityPoints += course.getQualityPoints();
            totalGradeUnits += course.getGradeUnit();
        }

        if (totalGradeUnits > 0) {
            return totalQualityPoints / totalGradeUnits;
        } else {
            return 0.0;
        }
    }
}

class Course {

    private final String nameAndCode;
    private final int courseUnit;
    private final String grade;
    private final int gradeUnit;

    public Course(String nameAndCode, int courseUnit, String grade, int gradeUnit) {
        this.nameAndCode = nameAndCode;
        this.courseUnit = courseUnit;
        this.grade = grade;
        this.gradeUnit = gradeUnit;
    }

    public String getNameAndCode() {
        return nameAndCode;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public String getGrade() {
        return grade;
    }

    public int getGradeUnit() {
        return gradeUnit;
    }

    public double getQualityPoints() {
        return courseUnit * gradeUnit;
    }
}
