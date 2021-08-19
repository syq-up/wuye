package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.User;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:44
 */
public interface UserService {
    int deleteByPrimaryKey(String ids);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectDataByPage(User user, Integer page, Integer limit);

    Integer selectDataCount(User user);

    List<User> selectDataByParam(User user);

    User selectLoginByParam(User user);
}
