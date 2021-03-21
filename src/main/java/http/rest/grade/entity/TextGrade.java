package http.rest.grade.entity;


import javax.persistence.Entity;

@Entity
public class TextGrade extends Grade {

    private TextGrade() {
    }

    @Override
    public String gradeAsString() {
        return null;
    }
}
