package com.shiyq.wuye.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.shiyq.wuye.entity.DO.UserComplaint;
import com.shiyq.wuye.mapper.UserComplaintMapper;

import java.util.List;

/**
 */
@Service
public class UserComplaintServiceImpl implements com.shiyq.wuye.service.UserComplaintService {

    private final UserComplaintMapper userComplaintMapper;

    // 注入
    public UserComplaintServiceImpl(UserComplaintMapper userComplaintMapper) {
        this.userComplaintMapper = userComplaintMapper;
    }

    /**
     * 根据主键删除
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        // 判断是否可以删除（未处理不可删除）
        List<UserComplaint> complaintList = userComplaintMapper.selectByIdAndStatus(ids);
        if(complaintList != null && complaintList.size() > 0) {
            return -500;
        }
        return userComplaintMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 全量插入
     * @param record
     * @return
     */
    @Override
    public int insert(UserComplaint record) {
        // 补充数据
        record.setCreateDate(DateUtil.now());
        record.setIsSolve("0");
        return userComplaintMapper.insert(record);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(UserComplaint record) {
        // 补充数据
        record.setCreateDate(DateUtil.now());
        record.setIsSolve("0");
        return userComplaintMapper.insertSelective(record);
    }

    /**
     * 根据主键查询单条数据
     *
     * @param id
     * @return
     */
    @Override
    public UserComplaint selectByPrimaryKey(Integer id) {
        return userComplaintMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(UserComplaint record) {
        return userComplaintMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(UserComplaint record) {
        return userComplaintMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询数据
     * @param userComplaint
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<UserComplaint> selectDataByPage(UserComplaint userComplaint, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页插件处理数据
        PageInfo<UserComplaint> pageInfo = new PageInfo<UserComplaint>(userComplaintMapper.selectDataByParam(userComplaint));
        // 返回数据
        return pageInfo.getList();
    }

    /**
     * 查询数据总量
     * @param userComplaint
     * @return
     */
    @Override
    public Integer selectDataCount(UserComplaint userComplaint) {
        return userComplaintMapper.selectDataCount(userComplaint);
    }
}
