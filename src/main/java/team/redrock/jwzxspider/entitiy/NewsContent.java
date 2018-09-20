package team.redrock.jwzxspider.entitiy;

import lombok.Data;

import java.util.List;

@Data
public class NewsContent {
    Long fileId;
    String title;
    List<String> content;
//    List<String> url;
}
