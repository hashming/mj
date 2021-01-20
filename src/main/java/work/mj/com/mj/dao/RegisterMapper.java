package work.mj.com.mj.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import work.mj.com.mj.pojo.Register;

@Mapper
public interface RegisterMapper {
    @Insert("insert into register (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(Register register);
}
