import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeData();
        displayMenu();
    }

    private static void initializeData() {
        courses.add(new Course("CS101", "Intro to Computer Science", "Basics of Computer Science", 30, "Mon 10-12"));
        courses.add(new Course("MA101", "Calculus I", "Differential and Integral Calculus", 25, "Tue 14-16"));
        courses.add(new Course("PH101", "Physics I", "Fundamentals of Physics", 20, "Wed 10-12"));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
        students.add(new Student("S003", "Charlie"));
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. List Students");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void listCourses() {
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void registerCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
        System.out.println("Course registered successfully.");
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = findStudentByID(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Course dropped successfully.");
    }

    private static void listStudents() {
        for (Student student : students) {
            System.out.println(student);
            for (Course course : student.getRegisteredCourses()) {
                System.out.println("  " + course.getCourseCode() + ": " + course.getTitle());
            }
        }
    }

    private static Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}