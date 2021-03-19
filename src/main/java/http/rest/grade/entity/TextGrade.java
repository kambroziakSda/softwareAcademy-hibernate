package http.rest.grade.entity;

import javax.persistence.Entity;

@Entity
public class TextGrade extends Grade {

    private String textGrade;

    public String getTextGrade() {
        return textGrade;
    }

    @Override
    public String gradeAsString() {
        return textGrade;
    }
}
