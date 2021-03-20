package http.rest.academy.entity;

import hibernate.core.VersionedEntity;
import http.rest.student.entity.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Academy extends VersionedEntity {

    @Id
    private String name;

    public Academy(String name) {
        this.name = name;
    }

    public Academy() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "academy_student", joinColumns = {@JoinColumn(name = "academy_name")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students;

    @Override
    public String toString() {
        return "Academy{" +
                "name='" + name + '\'' +
                ", students=" + students.size() +
                '}';
    }

    public Set<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }
}
