package http.rest.student.entity;

import hibernate.core.Address;
import http.rest.grade.entity.GradeDTO;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by krzysztof on 22.10.17.
 */
@XmlRootElement
public class StudentDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private List<GradeDTO> grades;

    private String city;

    private String street;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String firstName, String lastName, List<GradeDTO> grades, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = grades;
        this.city = address.getCity();
        this.street = address.getStreet();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return new Address(street, city);
    }

}
