package cn.mrchen.basic.controller;

import cn.mrchen.basic.entity.UserVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @PostMapping("/get")
    public String getToken(@RequestBody UserVO userVO) {
        String token="";
        token= JWT.create().withAudience(userVO.getUsername())
                .sign(Algorithm.HMAC256(userVO.getPassword()));
        return token;
    }
}
