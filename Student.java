import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Data {
    private Date dob;

    public Data(String dob) throws ParseException {
        SimpleDateFormat sdf;
        if (dob.contains("-")) {
            if (dob.split("-")[0].length() == 4) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else {
                sdf = new SimpleDateFormat("dd-MM-yyyy");
            }
            this.dob = new Date(sdf.parse(dob).getTime());  // Corrected casting
        } else {
            throw new IllegalArgumentException("Invalid date format");
        }
    }

    public int calculateAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(this.dob);

        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
            (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }
}

class Course {
    private String semester;
    private int marksObtained;

    public Course(String semester, int marksObtained) {
        this.semester = semester;
        this.marksObtained = marksObtained;
    }

    public String getSemester() {
        return semester;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    @Override
    public String toString() {
        return "Semester: " + semester + ", Marks Obtained: " + marksObtained;
    }
}

class Student {
    private String name;
    private Data data;
    private List<Course> courses;  // List to hold the courses the student is enrolled in

    public Student(String name, String dob) throws ParseException {
        this.name = name;
        this.data = new Data(dob);
        this.courses = new ArrayList<>();  // Initialize the list of courses
    }

    public void addCourse(Course course) {
        this.courses.add(course);  // Add a course to the student's list of courses
    }

    public void displayInfo() {
        int age = data.calculateAge();
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Courses:");
        for (Course course : courses) {
            System.out.println(course);  // Display each course information
        }
    }

    public static void main(String[] args) {
        try {
            Student student1 = new Student("John Doe", "15-05-2000");
            student1.addCourse(new Course("devopps 2024", 85));
            student1.addCourse(new Course("java 2025", 90));
            student1.displayInfo();

            Student student2 = new Student("Jane Smith", "2000-05-15");
            student2.addCourse(new Course("devopps 2024", 78));
            student2.addCourse(new Course("java 2025", 88));
            student2.displayInfo();

        } catch (ParseException e) {
            System.out.println("Error: Invalid date format");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
