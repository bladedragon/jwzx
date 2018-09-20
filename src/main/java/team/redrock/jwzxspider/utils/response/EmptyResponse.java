package team.redrock.jwzxspider.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmptyResponse {
    private int status;
    private String info;
    private String term;
    private String version;
    private String weekdayNum;
    private String buildNum;
    private Object data;
}
