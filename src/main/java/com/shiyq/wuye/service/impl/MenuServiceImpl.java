package com.shiyq.wuye.service.impl;

import org.springframework.stereotype.Service;
import com.shiyq.wuye.entity.DO.Menu;
import com.shiyq.wuye.mapper.MenuMapper;
/**
 */
@Service
public class MenuServiceImpl implements com.shiyq.wuye.service.MenuService {

    private final MenuMapper menuMapper;

    // 注入
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }


    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }


    @Override
    public Menu selectByPrimaryKey(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

}
