package http.rest.student.entity;

import hibernate.core.VersionedEntity;
import http.rest.grade.entity.Grade;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Student.FIND_BY_FIRST_AND_LAST_NAME, query = "SELECT e from Student e WHERE e.firstName = :firstName and e.lastName = :lastName ")
})
@Entity
public class Student extends VersionedEntity {


    public static final String FIND_BY_FIRST_AND_LAST_NAME = "Student.findByFirstAndLastName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(nullable = false)
    private String firstName;

    @Column(unique = true, nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Grade> grades;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public List<Grade> getGrades() {
        return grades;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
