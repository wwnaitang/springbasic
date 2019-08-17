package cn.mrchen.demo.controller;

import cn.mrchen.demo.entity.UserVO;
import cn.mrchen.pub.note.PassToken;
import cn.mrchen.pub.note.UseToken;
import cn.mrchen.pub.util.JJWTTokenCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/token")
@UseToken
//@PassToken
public class TokenController {

    @PostMapping("/get")
    @PassToken
    public String getToken(@RequestBody UserVO userVO, HttpServletResponse response) {
        JJWTTokenCreator tokenCreator = new JJWTTokenCreator();
        tokenCreator.setClaim("username", userVO.getUsername());
        String token = tokenCreator.createToken();
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return token;
    }

    @PostMapping("/check")
    @UseToken
//    @PassToken
    public String checkToken() {
        return "success";
    }

    @RequestMapping("/get2")
    @PassToken
    public String getToken2(HttpServletResponse response) {
        UserVO userVO = new UserVO();
        userVO.setUsername("a1");
        userVO.setPassword("123qwe");
        JJWTTokenCreator tokenCreator = new JJWTTokenCreator();
        tokenCreator.setClaim("username", userVO.getUsername());
        String token = tokenCreator.createToken();
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        // 测试将token放在header
        response.addHeader("token", token);  // 测试失败
        return token;
    }

    @RequestMapping("/check2")
//    @UseToken
    @PassToken
    public String checkToken2() {
        return "success";
    }
}
