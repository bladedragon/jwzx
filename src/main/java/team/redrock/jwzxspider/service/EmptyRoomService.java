package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.EmptyResponse;

public interface EmptyRoomService {
    public EmptyResponse getEmpty(String weekdayNum,String sectionNum,String buildNum,String week);
}
