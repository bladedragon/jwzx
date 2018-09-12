package team.redrock.jwzxspider.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {

    private int status;
    private String info;
    private String stuNum;
    private String idNum;
    private String term;
    private String version;
    private Object data;

}
