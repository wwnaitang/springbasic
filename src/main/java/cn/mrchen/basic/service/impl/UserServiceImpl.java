package cn.mrchen.basic.service.impl;

import cn.mrchen.basic.entity.UserVO;
import cn.mrchen.basic.service.IUserService;
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
