package team.redrock.jwzxspider.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.redrock.jwzxspider.entitiy.GradeInfo;
import team.redrock.jwzxspider.utils.analyzer.GradeAnalyzer;
import team.redrock.jwzxspider.utils.response.GradeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImp implements GradeService {
    @Value("${jwzx.chengji}")
    private String cjUrl;
    @Autowired
    private GradeAnalyzer gradeAnalyzer;

    @Override
    public GradeResponse getGradeInfo(String stu_num,String id_num) {
        GradeResponse response = new GradeResponse();
        Connection con = Jsoup.connect(cjUrl);
        List<GradeInfo> results = new ArrayList<>();
                //添加参数
                con.data("xh",stu_num);
                con.data("sfzh",id_num);
        //插入cookie（头文件形式）
//        con.header("Cookie", cookie);
        try {
            results = gradeAnalyzer.getGrade(con.post(),response);
        } catch (IOException e) {
                e.printStackTrace();
        }
        if(response.getStatus()!=415){
        response.setStatus(200);
        response.setInfo("success");
        response.setStuNum(stu_num);
        response.setIdNum(id_num);
        response.setVersion("1.0.0");
        response.setData(results);
        }

        return response;
    }
}
