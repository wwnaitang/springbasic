package cn.mrchen.pub.service;

import cn.mrchen.demo.entity.UserVO;

public interface IUserService {

    UserVO queryByUsername(String username);
}
