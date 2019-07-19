package cn.mrchen.basic.service;

import cn.mrchen.basic.entity.UserVO;

public interface IUserService {

    UserVO queryByUsername(String username);
}
