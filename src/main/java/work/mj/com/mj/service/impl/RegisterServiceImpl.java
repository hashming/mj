package work.mj.com.mj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.mj.com.mj.dao.RegisterMapper;
import work.mj.com.mj.pojo.Register;
import work.mj.com.mj.service.RegisterService;

import java.util.UUID;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public void setRegister(String registername,String token) {
        //头像链接
        String avatar_url = "https://cdn.jsdelivr.net/gh/hashming/PictureBed/data/20210202141338.png";
        //登录的用户信息数据库
        Register register = new Register();
        register.setToken(token);
        register.setName(registername);
        register.setAccountId("test");
        register.setGmtCreate(System.currentTimeMillis());
        register.setGmtModified(register.getGmtCreate());
        register.setAvatarUrl(avatar_url);
        registerMapper.insert(register);
    }

    @Override
    public Register findByToken(String token) {
        Register register = registerMapper.selectByToken(token);
        return register;
    }
}
