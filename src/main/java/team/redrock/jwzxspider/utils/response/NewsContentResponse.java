package team.redrock.jwzxspider.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsContentResponse {
    int status;
    String info;
    Long id;
    Object data;
}
