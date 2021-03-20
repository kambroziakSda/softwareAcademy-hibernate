package http.rest.grade.entity;

public class GradeDTO {

    private String value;

    public GradeDTO(String value) {
        this.value = value;
    }

    private GradeDTO() {
    }

    public String getValue() {
        return value;
    }

}
