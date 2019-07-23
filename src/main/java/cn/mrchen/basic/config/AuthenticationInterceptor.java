package cn.mrchen.basic.config;

import cn.mrchen.basic.exception.LoginException;
import cn.mrchen.basic.entity.UserVO;
import cn.mrchen.basic.note.PassToken;
import cn.mrchen.basic.note.UseToken;
import cn.mrchen.basic.service.IUserService;
import cn.mrchen.basic.util.JJWTTokenCreator;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;// 从 cookie 中取出 token
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class clazz = method.getDeclaringClass();
        // 检查是否有passtoken注释，有则跳过认证
        // 需要验证
        // 1. UseToken(class)
        // 2. UseToken(class + method)
        // 3. UseToken(method)
        // 不需要验证
        // 1. UseToken(class) + PassToken(method
        if (!method.isAnnotationPresent(UseToken.class) && !clazz.isAnnotationPresent(UseToken.class)
                || method.isAnnotationPresent(PassToken.class)) {
            if (!method.isAnnotationPresent(PassToken.class) && !clazz.isAnnotationPresent(PassToken.class)) {
                return true;
            }
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken == null) {
                passToken = (PassToken) clazz.getAnnotation(PassToken.class);
            }
            if (passToken.required()) {
                return true;
            }
            if (!clazz.isAnnotationPresent(UseToken.class)) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        UseToken useToken = method.getAnnotation(UseToken.class);
        if (useToken == null) {
            useToken = (UseToken) clazz.getAnnotation(UseToken.class);
        }
        if (useToken.required()) {
            // 执行认证
            if (token == null) {
//                throw new RuntimeException("无token，请重新登录");
                throw new LoginException("please login again");
            }
            // 获取 token 中的 user id
            String username;
            Claims claims = JJWTTokenCreator.parseToken(token);
            username = (String) claims.get("username");
            UserVO user = userService.queryByUsername(username);
            if (user == null) {
//                throw new RuntimeException("用户不存在，请重新登录");
                throw new LoginException("could not found this user");
            }
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
