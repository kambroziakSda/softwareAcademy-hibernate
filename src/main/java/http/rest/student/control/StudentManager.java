package http.rest.student.control;

import http.rest.RandomIdGenerator;
import http.rest.student.entity.Student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by krzysztof on 22.10.17.
 */
public class StudentManager {

    private static final Map<Integer, Student> STUDENTS = new ConcurrentHashMap<>();

    public static List<Student> getStudentByFirstAndLastName(String firstName, String lastName) {
        return STUDENTS.values()
                .stream()
                .filter(student -> nameMatches(firstName, lastName, student))
                .collect(Collectors.toList());
    }

    private static boolean nameMatches(String firstName, String lastName, Student student) {
        return student.getFirstName().equals(firstName) &&
                student.getLastName().equals(lastName);
    }

    public static Optional<Student> getStudentById(Integer id) {
        return Optional.ofNullable(STUDENTS.get(id));
    }

    public static Integer add(Student student) {
        int studentId = RandomIdGenerator.generateRandomId();
        student.setId(studentId);
        STUDENTS.put(studentId, student);
        return studentId;
    }

    public static List<Student> getAllStudents() {
        return new ArrayList<>(STUDENTS.values());
    }
}
