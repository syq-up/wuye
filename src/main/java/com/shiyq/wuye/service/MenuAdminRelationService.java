package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.MenuAdminRelation;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:41
 */
public interface MenuAdminRelationService {
    int insertSelective(MenuAdminRelation record);

    List<MenuAdminRelation> selectInfoByUserId(String userId);
}
