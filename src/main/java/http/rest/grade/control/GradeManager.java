package http.rest.grade.control;

import http.rest.academy.control.AcademyCrudException;
import http.rest.grade.entity.Grade;

import java.util.List;

public class GradeManager {

    public static void saveGrade(int studentId, int teacherId, String academyName, int value) throws AcademyCrudException {

    }

    public static void saveGrade(int studentId, int teacherId, String academyName, String text) throws AcademyCrudException {

    }

    public static List<Grade> findAllWithPagination(Integer page, Integer pageSize) {
      return null;
    }

    public static Double getAverage(int studentId, String academyName) throws AcademyCrudException {
        return null;
    }
}
