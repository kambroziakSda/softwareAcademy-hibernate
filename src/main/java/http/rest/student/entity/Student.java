package http.rest.student.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by krzysztof on 22.10.17.
 */
@XmlRootElement
public class Student {

    private Integer id;

    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
