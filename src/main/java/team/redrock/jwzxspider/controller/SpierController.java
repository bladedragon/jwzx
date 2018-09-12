package team.redrock.jwzxspider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import team.redrock.jwzxspider.Exception.StuidValidException;
import team.redrock.jwzxspider.service.GradeService;

import team.redrock.jwzxspider.utils.response.GradeResponse;

@Controller
public class SpierController {

    @Autowired
    private GradeService gradeService;

    @ResponseBody
    @PostMapping("/grade")
    public GradeResponse getGrade(String stuNum,String idNum)throws StuidValidException {
            GradeResponse response = new GradeResponse();
            response = gradeService.getGradeInfo(stuNum,idNum);
        if(response.getStatus() == 415 ){
    throw new StuidValidException("check failed");
}

        return response;
    }
}
