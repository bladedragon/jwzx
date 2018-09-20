package team.redrock.jwzxspider.service;

import org.apache.ibatis.annotations.Mapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import team.redrock.jwzxspider.DAO.GradeMapper;
import team.redrock.jwzxspider.entitiy.GradeInfo;
import team.redrock.jwzxspider.utils.VerifyUtil;
import team.redrock.jwzxspider.utils.analyzer.GradeAnalyzer;
import team.redrock.jwzxspider.utils.response.GradeResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "grade")
public class GradeServiceImp implements GradeService {
    @Value("${jwzx.chengji}")
    private String cjUrl;
    @Autowired
    private GradeAnalyzer gradeAnalyzer;
    @Autowired
    private GradeMapper gradeMapper;

    VerifyUtil verifyUtil = new VerifyUtil();


    @Override
    public GradeResponse getGradeInfoFromIf(String stu_num,String id_num) {
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
            for (GradeInfo gradeInfo: results) {
                gradeMapper.insert(gradeInfo);
            }
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

    @Cacheable(sync = true,value = "gradeUser",key = "#stu_name+#id_num")
    @Override
    public GradeResponse getGradeInfoFromDB(String stu_name,String id_num) {
        List<GradeInfo> result = new ArrayList<>();
        GradeResponse response = new GradeResponse();

            result = gradeMapper.SelectByStuNum(stu_name);
            System.out.println(result.isEmpty());
            if (!result.isEmpty()) {
                System.out.println("运行了数据库查询");
                response.setStatus(200);
                response.setInfo("success");
                response.setStuNum(stu_name);
                response.setIdNum(id_num);
                response.setVersion("1.0.0");
                response.setData(result);
                return response;
            }else{
                System.out.println("运行了接口查询");
                return getGradeInfoFromIf(stu_name,id_num);
            }


    }
    @CacheEvict(value = "gradeUser",allEntries = true)
    public void deleteGradeCache(){
        System.out.println("清除缓存");
    }


}
