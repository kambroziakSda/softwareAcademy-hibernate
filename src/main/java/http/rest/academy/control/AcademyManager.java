package http.rest.academy.control;

import http.rest.academy.entity.Academy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AcademyManager {

    public static Optional<Academy> findAcademyByName(String academyName) {
        return null;
    }

    public static List<Academy> findAll() {
        return null;
    }

    public static List<Academy> findAllWithStudents() {
        return null;
    }

    public static void saveAcademy(String name) {

    }

    public static void addStudentToAcademy(String academyName, Integer studentId) throws AcademyCrudException {

    }

    public static void removeFromAcademy(String academyName, Integer studentId) throws AcademyCrudException {
        System.out.println("removeFromAcademy start");

    }

    public static void addTeacherToAcademy(String academyName, Integer teacherId, BigDecimal salary) throws AcademyCrudException {
        System.out.println("addTeacherToAcademy start");

        System.out.println("addTeacherToAcademy end");
    }

    public static void removeTeacherFromAcademy(String academyName, Integer teacherId) throws AcademyCrudException {
        System.out.println("removeTeacherFromAcademy start");

        System.out.println("removeTeacherFromAcademy end");
    }
}
