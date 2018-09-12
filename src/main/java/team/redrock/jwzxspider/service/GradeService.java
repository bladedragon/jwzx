package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.GradeResponse;

public interface GradeService {
    public GradeResponse getGradeInfo(String stu_num,String id_num);
}
