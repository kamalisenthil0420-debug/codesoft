import java.io.*;
import java.util.*;


class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }
}


class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    
    public void removeStudent(int rollNumber) {
        boolean removed = students.removeIf(s -> s.getRollNumber() == rollNumber);
        if (removed)
            System.out.println(" Student removed successfully!");
        else
            System.out.println(" Student not found!");
    }

    
    public Student searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null;
    }

    
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println(" No students found.");
        } else {
            System.out.println("\n--- Student List ---");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("❌ Error saving data.");
        }
    }

    
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("📂 No previous data found.");
        }
    }
}


public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.loadFromFile();

        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println(" Please enter a valid number!");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    if (name.isEmpty()) {
                        System.out.println(" Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    if (grade.isEmpty()) {
                        System.out.println(" Grade cannot be empty!");
                        break;
                    }

                    sms.addStudent(new Student(name, roll, grade));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    int removeRoll = sc.nextInt();
                    sms.removeStudent(removeRoll);
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    int searchRoll = sc.nextInt();
                    Student found = sms.searchStudent(searchRoll);
                    if (found != null)
                        System.out.println(" Found: " + found);
                    else
                        System.out.println(" Student not found!");
                    break;

                case 4:
                    sms.displayStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to edit: ");
                    int editRoll = sc.nextInt();
                    sc.nextLine();

                    Student student = sms.searchStudent(editRoll);
                    if (student != null) {
                        System.out.print("Enter new Name (leave blank to skip): ");
                        String newName = sc.nextLine();

                        System.out.print("Enter new Grade (leave blank to skip): ");
                        String newGrade = sc.nextLine();

                        if (!newName.isEmpty()) student.setName(newName);
                        if (!newGrade.isEmpty()) student.setGrade(newGrade);

                        System.out.println(" Student updated!");
                    } else {
                        System.out.println(" Student not found!");
                    }
                    break;

                case 6:
                    sms.saveToFile();
                    System.out.println(" Data saved. Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
