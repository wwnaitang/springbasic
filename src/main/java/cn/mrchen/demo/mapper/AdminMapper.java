package cn.mrchen.demo.mapper;

import cn.mrchen.demo.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String pkAdmin);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String pkAdmin);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}