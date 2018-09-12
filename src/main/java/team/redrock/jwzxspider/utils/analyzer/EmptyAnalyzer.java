package team.redrock.jwzxspider.utils.analyzer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import team.redrock.jwzxspider.utils.response.EmptyResponse;

import java.util.ArrayList;
import java.util.List;

public class EmptyAnalyzer {

    public List<String> getEmptyRoomt(Document document, EmptyResponse response){
        List<String> rooms = new ArrayList<>();
        Elements table = document.body().getElementsByTag("table");

        table.forEach(tr->{
            tr.children().forEach(td->{
                String room  = td.attr("value");
                System.out.println(room);
                rooms.add(room);

            });
        });
        return rooms;
    }
}
