package com.shiyq.wuye.service.impl;

import org.springframework.stereotype.Service;
import com.shiyq.wuye.entity.DO.MenuAdminRelation;
import com.shiyq.wuye.mapper.MenuAdminRelationMapper;

import java.util.List;

/**
 */
@Service
public class MenuAdminRelationServiceImpl implements com.shiyq.wuye.service.MenuAdminRelationService {

    private final MenuAdminRelationMapper menuAdminRelationMapper;

    // 注入
    public MenuAdminRelationServiceImpl(MenuAdminRelationMapper menuAdminRelationMapper) {
        this.menuAdminRelationMapper = menuAdminRelationMapper;
    }

    @Override
    public int insertSelective(MenuAdminRelation record) {
        return menuAdminRelationMapper.insertSelective(record);
    }

    /**
     * 根据用户ID查询该用户的菜单权限
     * @param userId
     * @return
     */
    @Override
    public List<MenuAdminRelation> selectInfoByUserId(String userId) {
        return menuAdminRelationMapper.selectInfoByUserId(userId);
    }

}
