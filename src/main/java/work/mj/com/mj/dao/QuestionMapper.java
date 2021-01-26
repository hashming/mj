package work.mj.com.mj.dao;

import org.apache.ibatis.annotations.*;
import work.mj.com.mj.pojo.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    //查找所有问题
    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    //查找特定用户发布的问题
    @Select("select * from question where creator = #{registerId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("registerId") Integer registerId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    //所有问题数目
    @Select("select count(1) from question")
    Integer count();

    //特定用户发布的问题
    @Select("select count(1) from question where creator = #{registerId}")
    Integer countByUserId(@Param("registerId") Integer registerId);

    //根据用户查询问题
    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("UPDATE question SET view_count = view_count+1 WHERE id = #{id}")
    Boolean updateViewCountById(@Param("id") Integer id);
}
