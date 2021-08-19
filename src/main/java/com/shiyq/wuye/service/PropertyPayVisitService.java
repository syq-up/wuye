package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.PropertyPayVisit;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:43
 */
public interface PropertyPayVisitService {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyPayVisit record);

    int insertSelective(PropertyPayVisit record);

    PropertyPayVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyPayVisit record);

    int updateByPrimaryKey(PropertyPayVisit record);

    List<PropertyPayVisit> selectDataByPage(PropertyPayVisit propertyPayVisit, Integer page, Integer limit);

    Integer selectDataCount(PropertyPayVisit propertyPayVisit);
}
