package cn.mrchen.basic.controller;

import cn.mrchen.basic.entity.UserVO;
import cn.mrchen.basic.note.PassToken;
import cn.mrchen.basic.note.UseToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @PostMapping("/get")
    @PassToken
    public String getToken(@RequestBody UserVO userVO) {
        String token="";
        token= JWT.create().withAudience(userVO.getUsername())
                .sign(Algorithm.HMAC256(userVO.getPassword()));
        return token;
    }

    @PostMapping("/check")
    @UseToken
    public String checkToken() {
        return "success";
    }

    @RequestMapping("/get2")
    @PassToken
    public String getToken2(HttpServletResponse response) {
        UserVO userVO = new UserVO();
        userVO.setUsername("a1");
        userVO.setPassword("123qwe");
        String token = JWT.create().withAudience(userVO.getUsername()).sign(Algorithm.HMAC256(userVO.getPassword()));
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return "success";
    }

    @RequestMapping("/check2")
    @UseToken
    public String checkToken2() {
        return "success";
    }
}
