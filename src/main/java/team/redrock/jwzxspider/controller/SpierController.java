package team.redrock.jwzxspider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import team.redrock.jwzxspider.Exception.StuidValidException;
import team.redrock.jwzxspider.service.*;


import team.redrock.jwzxspider.utils.NormalUtils;
import team.redrock.jwzxspider.utils.response.EmptyResponse;
import team.redrock.jwzxspider.utils.response.GradeResponse;
import team.redrock.jwzxspider.utils.response.NewsContentResponse;
import team.redrock.jwzxspider.utils.response.NewslistResponse;



@Controller
public class SpierController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private EmptyRoomService emptyRoomService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private VerifyService verifyService;



    @ResponseBody
    @PostMapping("/grade")
    public GradeResponse getGrade(String stuNum,String idNum,@RequestParam(defaultValue = "false") String ForceFetch)throws StuidValidException {
            GradeResponse response = new GradeResponse();
            response = verifyService.getGrade(stuNum,idNum);

            if(ForceFetch.equals("true")){
                gradeService.deleteGradeCache();
            }
            if(response.getStatus() == 415 ){
                throw new StuidValidException("check failed");
}
        return response;
    }


    @ResponseBody
    @PostMapping("/empty")
    public EmptyResponse getEmpty(String weekdayNum,String sectionNum, String buildNum,String week,@RequestParam(defaultValue = "false") String ForceFetch)throws StuidValidException {
        NormalUtils normalUtils = new NormalUtils();
            EmptyResponse result = new EmptyResponse();
            EmptyResponse response = new EmptyResponse();
//            response = emptyRoomService.selectEmpty(Integer.parseInt(weekdayNum),Integer.parseInt(sectionNum),Integer.parseInt(week));
        String section = normalUtils.getSection(sectionNum,response);

        if(!buildNum.equals("null")){
             result = emptyRoomService.getRoom(weekdayNum,section,week,buildNum);

        }
        if(ForceFetch.equals("true")){
            emptyRoomService.deleteCahce();
        }
        if(result.getStatus() == -1){
            throw new StuidValidException("inner error");
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/NewsContent")
    public NewsContentResponse getcontent(Long id,@RequestParam(defaultValue = "false") String ForceFetch)throws StuidValidException{
    NewsContentResponse response = new NewsContentResponse();

    response = newsService.getContentFromDb(id);

    if(ForceFetch.equals("true")){
        newsService.deleteNewslist();
    }
        if(response.getStatus() == -20){
            throw new StuidValidException("Invaild param: id");
        }
    return response;
    }

    @ResponseBody
    @PostMapping("/NewsList")
    public NewslistResponse getList(int page,@RequestParam(defaultValue = "false") String ForceFetch){
        NewslistResponse response = new NewslistResponse();
            response = newsService.getListFromDB(page);
        if(ForceFetch.equals("true")){
            newsService.deleteNewslist();
        }

        return response;
    }


}
