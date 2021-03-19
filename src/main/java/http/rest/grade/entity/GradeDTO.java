package http.rest.grade.entity;

public class GradeDTO {

    private String value;

    private int teacherId;

    private String academyName;

    public GradeDTO(String value, int teacherId, String academyName) {
        this.value = value;
        this.teacherId = teacherId;
        this.academyName = academyName;
    }

    private GradeDTO() {
    }

    public String getValue() {
        return value;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getAcademyName() {
        return academyName;
    }
}
