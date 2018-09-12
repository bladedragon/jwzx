package team.redrock.jwzxspider.entitiy;

import lombok.Data;

import java.io.Serializable;

@Data
public class GradeInfo implements Serializable {
    private String course;
    private String grade;
    private String property;
    private String status;
    private String student;
    private String term;
}
