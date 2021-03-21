package http.rest.teacher.entity;

import hibernate.core.VersionedEntity;
import http.rest.academy.entity.TeacherInAcademy;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherInAcademy> academies;

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public Set<TeacherInAcademy> getAcademies() {
        return academies;
    }
}
