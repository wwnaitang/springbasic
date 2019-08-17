package cn.mrchen.demo.service.impl;

import cn.mrchen.demo.entity.UserVO;
import cn.mrchen.pub.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public UserVO queryByUsername(String username) {
        UserVO userVO = new UserVO();
        userVO.setUsername(username);
        userVO.setPassword("123qwe");
        return userVO;
    }
}
