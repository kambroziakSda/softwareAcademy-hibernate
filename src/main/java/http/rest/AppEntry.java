package http.rest;

import http.rest.academy.boundary.AcademyResource;
import http.rest.academy.entity.Academy;
import http.rest.grade.boundary.GradeResource;
import http.rest.grade.entity.Grade;
import http.rest.student.boundary.StudentsResource;
import http.rest.teacher.boundary.TeacherResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krzysztof on 14.10.17.
 */
@ApplicationPath("/")
public class AppEntry extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes= new HashSet<Class<?>>();
        classes.add(StudentsResource.class);
        classes.add(TeacherResource.class);
        classes.add(AcademyResource.class);
        classes.add(GradeResource.class);
        return classes;
    }
}
