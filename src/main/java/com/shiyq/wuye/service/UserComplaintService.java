package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.UserComplaint;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:44
 */
public interface UserComplaintService {
    int deleteByPrimaryKey(String ids);

    int insert(UserComplaint record);

    int insertSelective(UserComplaint record);

    UserComplaint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComplaint record);

    int updateByPrimaryKey(UserComplaint record);

    List<UserComplaint> selectDataByPage(UserComplaint userComplaint, Integer page, Integer limit);

    Integer selectDataCount(UserComplaint userComplaint);
}
