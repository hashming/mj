package work.mj.com.mj.service;

import work.mj.com.mj.pojo.Register;

/**
 * 存储用户的登录信息到数据库
 */
public interface RegisterService {
    //登录信息写入到数据库
    void setRegister(String registername,String token);

    Register findByToken(String token);
}
