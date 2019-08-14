package cn.mrchen.basic.mapper;

import cn.mrchen.basic.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String pkAdmin);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String pkAdmin);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}