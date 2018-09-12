package team.redrock.jwzxspider.utils.response;

import lombok.Data;

@Data
public class EmptyResponse {
    private int status;
    private String info;
    private String term;
    private String version;
    private String weekdayNum;
    private String buildNum;
    private Object data;
}
