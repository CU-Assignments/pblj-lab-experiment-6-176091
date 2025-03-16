// implement Java program that uses lambda expressions and Stream API to filter students who scored above 75%, sort them by marks, and display their names.

// Step 1: Create the Student Class
// - Define a Student class with attributes:
//     name (String)
//     marks (double)
// - Implement a constructor to initialize these values.
// - Add a display method to print student details.

// Step 2: Create the StudentFilterSort Class
// - Create a list of students with sample data.
// - Use Streams Class to:
//       Filter students who scored above 75%.
//       Sort students by marks in descending order.
//       Collect the results into a new list.
// - Use forEach() with a method reference to display results.ts.
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public void display() {
        System.out.println(name);
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        runTestCase("Case 1: Normal Case", Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 72),
            new Student("Charlie", 90),
            new Student("David", 65),
            new Student("Eve", 85)
        ));

        runTestCase("Case 2: All Below 75%", Arrays.asList(
            new Student("Bob", 70),
            new Student("David", 60),
            new Student("Frank", 65)
        ));

        runTestCase("Case 3: Same Marks", Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 80),
            new Student("Charlie", 85)
        ));

        runTestCase("Case 4: Single Student Above 75%", Arrays.asList(
            new Student("Alice", 60),
            new Student("Bob", 50),
            new Student("Charlie", 90)
        ));
    }

    private static void runTestCase(String caseName, List<Student> students) {
        System.out.println("\n" + caseName);

        List<Student> filteredSortedStudents = students.stream()
            .filter(s -> s.marks > 75) // Filter students with marks > 75
            .sorted((s1, s2) -> {
                int markComparison = Double.compare(s2.marks, s1.marks);
                return markComparison != 0 ? markComparison : s1.name.compareTo(s2.name);
            }) // Sort by marks descending, then by name ascending
            .collect(Collectors.toList()); // Collect into a new list

        if (filteredSortedStudents.isEmpty()) {
            System.out.println("No student scored above 75%.");
        } else {
            filteredSortedStudents.forEach(Student::display);
        }
    }
}


// Test Case	                        Input Data	                                                 Expected Output
// Case 1:         Normal Case	Alice (80), Bob (72), Charlie (90), David (65), Eve (85)	         Charlie, Eve, Alice (Sorted by marks)
// Case 2:         All Below 75%	Bob (70), David (60), Frank (65)	                               No output (Empty List)
// Case 3:         Same Marks	Alice (80), Bob (80), Charlie (85)                                 Charlie, Alice, Bob (Sorted by marks, then by name)
// Case 4:         Single Student Above 75%	Alice (60), Bob (50), Charlie (90)	                 Charlie (Only one student)
