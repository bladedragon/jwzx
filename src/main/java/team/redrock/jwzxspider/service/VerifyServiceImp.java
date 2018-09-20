package team.redrock.jwzxspider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.redrock.jwzxspider.utils.VerifyUtil;
import team.redrock.jwzxspider.utils.response.GradeResponse;
import team.redrock.jwzxspider.utils.response.NewslistResponse;

@Service
public class VerifyServiceImp implements VerifyService{
    VerifyUtil verifyUtil = new VerifyUtil();
    @Autowired
    private GradeService gradeService;

    public GradeResponse getGrade(String stu_name,String id_num){
//        List<GradeInfo> result = new ArrayList<>();
        GradeResponse response = new GradeResponse();
        if (verifyUtil.verifyIdentity(stu_name,id_num)){
            return gradeService.getGradeInfoFromDB(stu_name,id_num);
        }else{
            response.setStatus(415);
            return response;
        }
    }

}
