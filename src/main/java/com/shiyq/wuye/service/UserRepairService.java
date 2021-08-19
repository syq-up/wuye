package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.UserRepair;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:44
 */
public interface UserRepairService {
    int deleteByPrimaryKey(String ids);

    int insert(UserRepair record);

    int insertSelective(UserRepair record);

    UserRepair selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRepair record);

    int updateByPrimaryKey(UserRepair record);

    List<UserRepair> selectDataByPage(UserRepair userRepair, Integer page, Integer limit);

    Integer selectDataCount(UserRepair userRepair);
}
