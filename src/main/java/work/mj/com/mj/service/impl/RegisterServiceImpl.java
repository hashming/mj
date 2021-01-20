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
    public void setRegister(String registername) {
        Register register = new Register();
        register.setToken(UUID.randomUUID().toString());
        register.setName(registername);
        register.setAccountId("test");
        register.setGmtCreate(System.currentTimeMillis());
        register.setGmtModified(register.getGmtCreate());
        registerMapper.insert(register);
    }
}
