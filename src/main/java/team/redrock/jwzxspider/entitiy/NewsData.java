package team.redrock.jwzxspider.entitiy;

import lombok.Data;

@Data
public class NewsData {
    int totalCount;
    int fileId;
    String dirId;
    String title;
    String pubId;
    String pubTime;
    String pubIp;
    int readCount;
    String dirName;
    String teaName;
    int days;

}
