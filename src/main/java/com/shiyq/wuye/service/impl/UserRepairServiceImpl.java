package com.shiyq.wuye.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.entity.DO.UserRepair;
import com.shiyq.wuye.mapper.UserRepairMapper;

import java.util.List;

/**
 */
@Service
public class UserRepairServiceImpl implements com.shiyq.wuye.service.UserRepairService {

    private final UserRepairMapper userRepairMapper;

    // 注入
    public UserRepairServiceImpl(UserRepairMapper userRepairMapper) {
        this.userRepairMapper = userRepairMapper;
    }

    /**
     * 根据主键删除
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        // 判断是否可以删除（未处理不可删除）
        List<UserRepair> repairs = userRepairMapper.selectByIdAndStatus(ids);
        if(repairs != null && repairs.size() > 1) {
            return -500;
        }
        return userRepairMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 全量插入
     * @param record
     * @return
     */
    @Override
    public int insert(UserRepair record) {
        // 补充数据
        record.setCreateDate(DateUtil.now());
        record.setIsSolve("0");
        return userRepairMapper.insert(record);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(UserRepair record) {
        // 补充数据
        record.setCreateDate(DateUtil.now());
        record.setIsSolve("0");
        return userRepairMapper.insertSelective(record);
    }

    /**
     * 根据主键查询单条数据
     * @param id
     * @return
     */
    @Override
    public UserRepair selectByPrimaryKey(Integer id) {
        return userRepairMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(UserRepair record) {
        return userRepairMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(UserRepair record) {
        return userRepairMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询全部数据
     * @param userRepair    查询条件
     * @param page          当前页
     * @param limit         每页显示的数据量
     * @return
     */
    @Override
    public List<UserRepair> selectDataByPage(UserRepair userRepair, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页插件查询
        PageInfo<UserRepair> pageInfo = new PageInfo<UserRepair>(userRepairMapper.selectDataByParam(userRepair));
        // 返回数据
        return pageInfo.getList();
    }

    /**
     * 查询数据总量
     * @param userRepair
     * @return
     */
    @Override
    public Integer selectDataCount(UserRepair userRepair) {
        return userRepairMapper.selectDataCount(userRepair);
    }
}
