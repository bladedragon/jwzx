package team.redrock.jwzxspider.entitiy;

import lombok.Data;

import java.util.List;

@Data
public class NewsInfo {
    int totalPage;
    List<NewsData> data;
}
