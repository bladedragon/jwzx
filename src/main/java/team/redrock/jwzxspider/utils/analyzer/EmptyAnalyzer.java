package team.redrock.jwzxspider.utils.analyzer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import team.redrock.jwzxspider.utils.response.EmptyResponse;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmptyAnalyzer {

    public List<String> getEmptyRoomt(Document document, EmptyResponse response){
        List<String> rooms = new ArrayList<>();
        Elements table = document.body().getElementsByTag("tbody").get(0).children();
//        System.out.println(table);
        table.forEach(tr->{
            tr.children().forEach(td->{
//                System.out.println("td:"+td);
                String room  = td.getElementsByTag("input").attr("value");
//                System.out.println(room);

                rooms.add(room);

            });
        });

        return rooms;
    }
}
