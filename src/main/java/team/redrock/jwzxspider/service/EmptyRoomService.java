package team.redrock.jwzxspider.service;

import team.redrock.jwzxspider.utils.response.EmptyResponse;

import java.util.List;

public interface EmptyRoomService {
    public EmptyResponse getRoom(String weekdayNum,String sectionNum,String week,String buildNum);
    public List<String> selectEmpty(String weekdayNum,String sectionNum,String week,EmptyResponse emptyResponse);
    public void saveRoom();
    public List<String> getRooms(String weekdayNum, String sectionNum, String week,String buildName);
    public void deleteCahce();
}
