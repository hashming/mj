package work.mj.com.mj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import work.mj.com.mj.pojo.Register;

@Mapper
public interface RegisterMapper {
    @Insert("insert into register (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(Register register);

    @Select("select * from register where token = #{token}")
    Register findByToken(@Param("token") String token);

    @Select("select * from register where id = #{id}")
    Register findById(@Param("id")Integer id);
}
