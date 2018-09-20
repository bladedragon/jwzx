package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.GradeResponse;

public interface GradeService {
    public GradeResponse getGradeInfoFromIf(String stu_num,String id_num);
    public GradeResponse getGradeInfoFromDB(String stu_name,String id_num);
    public void deleteGradeCache();
}
