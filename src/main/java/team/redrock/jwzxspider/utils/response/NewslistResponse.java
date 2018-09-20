package team.redrock.jwzxspider.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewslistResponse {
    int status;
    String info;
    int page;
    Object data;
}
