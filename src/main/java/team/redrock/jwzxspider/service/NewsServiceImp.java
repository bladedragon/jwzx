package team.redrock.jwzxspider.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import team.redrock.jwzxspider.DAO.NewsContentMapper;
import team.redrock.jwzxspider.DAO.NewsListMapper;
import team.redrock.jwzxspider.entitiy.NewsContent;
import team.redrock.jwzxspider.entitiy.NewsData;
import team.redrock.jwzxspider.entitiy.NewsInfo;
import team.redrock.jwzxspider.utils.UrlConnectUtil;
import team.redrock.jwzxspider.utils.analyzer.NewsContentAnalyzer;
import team.redrock.jwzxspider.utils.response.NewsContentResponse;
import team.redrock.jwzxspider.utils.response.NewslistResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "newslist")
public class NewsServiceImp implements NewsService {
    @Value("${jwzx.newslist}")
    private String nlUrl;
    @Value("${jwzx.newscontent}")
    private String ncUrl;
    @Autowired
    private NewsContentAnalyzer newsContentAnalyzer;
    @Autowired
    private NewsListMapper newsListMapper;
    @Autowired
    private NewsContentMapper newsContentMapper;


    @Override
    public NewslistResponse getListFromIp(int page) {
        int size = 10;
        if(page == 0 ){
            page = 1;
        }
    String url = nlUrl+"pageNo="+page+"&pageSize="+size+"&searchKey=";
        UrlConnectUtil urlConnectUtil = new UrlConnectUtil();
        NewslistResponse response = new NewslistResponse();
        JSONObject object = urlConnectUtil.getNewsList(url);
        NewsInfo newsInfo = JSONObject.toJavaObject(object,NewsInfo.class);
//        System.out.println(newsInfo);
//        for (NewsData a: newsInfo.getData()) {
//            newsListMapper.insert(a);
//        }
        response.setData(newsInfo.getData());
        response.setStatus(200);
        response.setInfo("success");
        response.setPage(page);

        return response;

    }

    @Cacheable(sync = true,value = "newsUser",key = "#page")
    @Override
    public NewslistResponse getListFromDB(int page){
        List<NewsData> data = new ArrayList<>();
        NewsInfo newsInfo = new NewsInfo();
        NewslistResponse response = new NewslistResponse();

//        PageHelper.startPage(page,10);
//        List<NewsData> dirId = newsListMapper.SelectByPage(page);
//        data = newsListMapper.SelectAll();
//        System.out.println("page:"+dirId);
//        if(dirId.isEmpty()){
         return getListFromIp(page);
//        }else {
//            PageInfo result = new PageInfo(data);
//            System.out.println(result);
//            newsInfo.setData(result.getList());
//            newsInfo.setTotalPage(result.getPageSize());
//            response.setData(newsInfo.getData());
//            response.setStatus(200);
//            response.setInfo("success");
//            response.setPage(page);
//            return response;
//        }
    }



    @Override
    public NewsContentResponse getContentFromIp(Long id) {
        String url=ncUrl+"id="+id;
        Connection con = Jsoup.connect(url).maxBodySize(0);
        NewsContentResponse response = new NewsContentResponse();
        NewsContent results = new NewsContent();

        if(id == null)
        response.setStatus(-20);
        try {
             results = newsContentAnalyzer.getNewsContent(con.get(),response);
             results.setFileId(id);
//             newsContentMapper.insert(id,results.getTitle(),results.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.getStatus()!=-20) {
            response.setData(results);
            response.setId(id);
            response.setStatus(200);
            response.setInfo("success");}
            return response;
    }

    @Override
    @Cacheable(sync = true,value = "newsUser",key = "#id")
    public NewsContentResponse getContentFromDb(Long id){
        NewsContent content = new NewsContent();
        NewsContentResponse response = new NewsContentResponse();
//        content = newsContentMapper.SelectAll();
//        if(content!=null){
//            System.out.println("新闻内容用数据库查询");
//            response.setData(content);
//            response.setId(id);
//            response.setStatus(200);
//            response.setInfo("success");
//        }else{
            System.out.println("新闻内容用接口查询");
            System.out.println("id="+id);
            return getContentFromIp(id);
//        }
//        return response;
    }
    @CacheEvict(value = "newsUser",allEntries = true)
    public void deleteNewslist(){
        System.out.println("清除缓存");
    }

}
