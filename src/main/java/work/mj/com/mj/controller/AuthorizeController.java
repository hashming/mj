package work.mj.com.mj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import work.mj.com.mj.dto.AccessTokenDto;
import work.mj.com.mj.dto.GithubUser;
import work.mj.com.mj.provider.GithubProvider;

public class AuthorizeController {

    @Autowired
    public GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,@RequestParam(name = "state")String state){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("f2e9353d01dd5e9dea92");
        accessTokenDto.setClient_secret("1f80e5eedce10e4dca96e111cbbbfaa38d8eb5e8");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDto.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());

        githubProvider.getAccessToken(accessTokenDto);
        return "index";
    }

}
