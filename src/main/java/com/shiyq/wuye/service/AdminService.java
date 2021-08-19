package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.Admin;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:39
 */
public interface AdminService {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectByAdmin(Admin admin);

    Admin selectByLogin(Admin admin);

    List<Admin> selectDataByPage(Admin admin, Integer page, Integer limit);

    Integer selectDataCount(Admin admin);
}
