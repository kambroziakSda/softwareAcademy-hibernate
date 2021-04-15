package http.rest.student.entity;

import hibernate.core.VersionedEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student extends VersionedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public static final String FIND_BY_FIRST_AND_LAST_NAME = "Student.findByFirstAndLastName";

    private String firstName;

    Student() {
    }

    public Student(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
