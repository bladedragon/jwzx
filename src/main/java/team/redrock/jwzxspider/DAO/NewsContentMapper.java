package team.redrock.jwzxspider.DAO;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team.redrock.jwzxspider.entitiy.NewsContent;
import team.redrock.jwzxspider.entitiy.NewsData;

import java.util.List;

@Mapper
@Repository
@Transactional
public interface NewsContentMapper {
    @Insert("Insert into newsContent(id,title,content) values(#{id},#{title},#{content})")
    void insert(@Param("id") Long id, @Param("title") String title, @Param("content") String content);

    @Select({"select * from newsContent"})
    NewsContent SelectAll();


}
