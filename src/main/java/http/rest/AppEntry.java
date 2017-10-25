package http.rest;

import http.rest.course.boundary.CoursesResource;
import http.rest.developmentschool.boundary.DevelomentSchoolsResource;
import http.rest.student.boundary.StudentsResource;

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
        classes.add(CoursesResource.class);
        classes.add(DevelomentSchoolsResource.class);
        classes.add(StudentsResource.class);
        return classes;
    }
}
