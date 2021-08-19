package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.Menu;

/**
 * @author shiyq
 * @create 2021-06-09 18:42
 */
public interface MenuService {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}
