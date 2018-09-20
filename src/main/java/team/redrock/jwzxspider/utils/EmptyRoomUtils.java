package team.redrock.jwzxspider.utils;

import team.redrock.jwzxspider.utils.response.EmptyResponse;

public class EmptyRoomUtils {
    public EmptyResponse getEmptyFromDB(int weekdayNum,int sectionNum,int buildNum,int week ){
        EmptyResponse emptyResponse = new EmptyResponse();

        emptyResponse.setStatus(200);
        emptyResponse.setBuildNum(String.valueOf(buildNum));
        emptyResponse.setInfo("success");
        emptyResponse.setVersion("1.0.0");
        return emptyResponse;
    }
}
