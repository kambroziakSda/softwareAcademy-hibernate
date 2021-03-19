package http.rest.teacher.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NamePK implements Serializable {

    private String firstName;

    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamePK namePK = (NamePK) o;
        return Objects.equals(firstName, namePK.firstName) && Objects.equals(lastName, namePK.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
