package team.redrock.jwzxspider.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import team.redrock.jwzxspider.service.EmptyRoomService;
import team.redrock.jwzxspider.service.EmptyRoomServiceImp;
import team.redrock.jwzxspider.utils.response.EmptyResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EmptyUrl {


     private static EmptyRoomService emptyRoomService;
    @Autowired
    RedisTemplate redisTemplate;

    private static Lock lock = new ReentrantLock();
    private static List<List<String>> urlList = new ArrayList();
    private static ApplicationContext ctx = null;

    public List<List<String>> getList(){
        if(urlList.isEmpty()){
          List<String> tem = new ArrayList<>();
          tem.add("content is null");
            urlList.add(tem);
        }
        return urlList;
    }
    public void getempty(String weekdayNum,String week,String sectionNum){
        emptyRoomService = SpringContextUtils.getBean("EmptyRoomService",EmptyRoomService.class);


        lock.lock();
        List<String> result = new ArrayList<>();
        System.out.println("w:"+week);
        try{
                result = this.emptyRoomService.getRooms(weekdayNum,sectionNum,week,"0");

//           redisTemplate.opsForValue().set(weekdayNum+"-"+sectionNum+"-"+week,result);
            System.out.println("____"+result+"______");
//              Thread.sleep(5);
            urlList.add(result);       //添加url到url列表
        }catch(Exception ex ){
        }
        finally {
            lock.unlock();          // 解锁
        }
    }

}
