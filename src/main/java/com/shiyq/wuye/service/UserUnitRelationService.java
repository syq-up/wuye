package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.UserUnitRelation;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:45
 */
public interface UserUnitRelationService {
    int deleteByPrimaryKey(String ids);

    int insert(UserUnitRelation record);

    int insertSelective(UserUnitRelation record);

    UserUnitRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserUnitRelation record);

    int updateByPrimaryKey(UserUnitRelation record);

    List<UserUnitRelation> selectDataByPage(UserUnitRelation userUnitRelation, Integer page, Integer limit);

    Integer selectDataCount(UserUnitRelation userUnitRelation);
}
