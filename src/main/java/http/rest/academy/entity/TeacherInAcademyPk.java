package http.rest.academy.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherInAcademyPk implements Serializable {

    private String academyName;

    private Integer teacherId;

    TeacherInAcademyPk() {
    }

    public TeacherInAcademyPk(String academyName, Integer teacherId) {
        this.academyName = academyName;
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherInAcademyPk that = (TeacherInAcademyPk) o;
        return Objects.equals(academyName, that.academyName) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(academyName, teacherId);
    }
}
