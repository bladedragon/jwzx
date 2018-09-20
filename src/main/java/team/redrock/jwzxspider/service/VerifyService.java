package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.GradeResponse;

public interface VerifyService {
    public GradeResponse getGrade(String stu_name, String id_num);
}
