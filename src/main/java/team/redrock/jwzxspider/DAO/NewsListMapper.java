package team.redrock.jwzxspider.DAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.redrock.jwzxspider.entitiy.GradeInfo;
import team.redrock.jwzxspider.entitiy.NewsData;

import java.util.List;

@Mapper
@Repository
@Transactional
public interface NewsListMapper {

    @Insert("Insert into newslist(totalCount,fileId,dirId,title,pubId,pubTime,pubIp,readCount,dirName,teaName,days) values(#{totalCount},#{fileId},#{dirId},#{title},#{pubId},#{pubTime},#{pubIp},#{readCount},#{dirName},#{teaName},#{days})")
    void insert(NewsData newsData);

    @Select("select * from newslist")
    List<NewsData> SelectAll();

    @Select("select * from newslist where dirId = #{page}")
    List<NewsData> SelectByPage(@Param("page") int page);

}
