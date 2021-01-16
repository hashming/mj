package work.mj.com.mj.service;

import org.springframework.stereotype.Component;

public interface LoginUser {

    public Boolean judgeOuth(String name, String password);
}
