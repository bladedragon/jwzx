package team.redrock.jwzxspider.entitiy;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class EmptyRoom implements Serializable {
    private List<String> data;
}
