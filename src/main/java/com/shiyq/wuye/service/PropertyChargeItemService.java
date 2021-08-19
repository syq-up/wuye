package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.PropertyChargeItem;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:42
 */
public interface PropertyChargeItemService {
    int deleteByPrimaryKey(String ids);

    int insert(PropertyChargeItem record);

    int insertSelective(PropertyChargeItem record);

    PropertyChargeItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyChargeItem record);

    int updateByPrimaryKey(PropertyChargeItem record);

    List<PropertyChargeItem> selectDataByPage(PropertyChargeItem propertyChargeItem, Integer page, Integer limit);

    Integer selectDataCount(PropertyChargeItem propertyChargeItem);

    List<PropertyChargeItem> selectDataAllInfo(PropertyChargeItem param);
}
