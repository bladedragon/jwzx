package team.redrock.jwzxspider.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import team.redrock.jwzxspider.service.EmptyRoomService;
import team.redrock.jwzxspider.service.EmptyRoomServiceImp;
import team.redrock.jwzxspider.utils.response.EmptyResponse;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Scope("prototype")
public class MyThreading implements InitializingBean {
//    private static String weekdayNum= null;
//    private static String week= null;
//    private static String sectiondayNum= null;
    private static EmptyUrl urls = null;

//    @Autowired
    EmptyRoomService emptyRoomService;


//    public MyThreading(String weekdayNum,String week,String sectiondayNum){
//        this.week = week ;
//        this.weekdayNum = weekdayNum;
//        this.sectiondayNum=sectiondayNum;
//    }
    public MyThreading(EmptyRoomService emptyRoomService){
this.emptyRoomService = emptyRoomService;
    }

    public String getUrlList(){

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 25 ; i ++){
            for(int j =1 ;j<8;j++){
                for(int k =0; k<5;k++){
                    executor.execute(new AddEmpty(String.valueOf(j),String.valueOf(i),"12"));
                    System.out.println("线程"+i+"-"+j+"-"+k+"-启动！");
                }
            }

        }
        executor.shutdown();
        System.out.println("执行关闭");
        while (!executor.isTerminated()){}
//        return urls.getList();
    return "返回参数";
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public static class AddEmpty implements Runnable{
        String week;
        String weekdayNum;
        String sectiondayNum;
private static ApplicationContext ctx = null;
private static EmptyRoomService emptyRoomService;
//EmptyRoomServiceImp emptyRoomServiceImp = new EmptyRoomServiceImp();
        static{
            ctx = new ClassPathXmlApplicationContext(
                    "classpath:/spring-context*.xml");
            emptyRoomService = (EmptyRoomService) ctx.getBean("emptyRoomService");
        }
         AddEmpty(String weekdayNum,String week,String sectiondayNum){
            this.week = week ;
            this.weekdayNum = weekdayNum;
            this.sectiondayNum=sectiondayNum;
        }
        public void run(){
            System.out.println(weekdayNum+"-"+week+"-"+sectiondayNum);
//            urls.getempty(weekdayNum,week,sectiondayNum);
            List<String> result = emptyRoomService.getRooms(weekdayNum,sectiondayNum,week,"0");
            System.out.println("启动任务 "+result);// 启动多线程任务
        }
    }
}

