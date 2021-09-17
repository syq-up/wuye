package com.shiyq.wuye.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyq.wuye.mapper.PropertyChargeVisitMapper;
import com.shiyq.wuye.entity.DO.PropertyChargeVisit;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.entity.DO.PropertyPayVisit;
import com.shiyq.wuye.mapper.PropertyPayVisitMapper;

import java.util.List;

/**
 */
@Service
public class PropertyPayVisitServiceImpl implements com.shiyq.wuye.service.PropertyPayVisitService {

    private final PropertyPayVisitMapper propertyPayVisitMapper;
    private final PropertyChargeVisitMapper chargeVisitMapper;

    // 注入
    public PropertyPayVisitServiceImpl(PropertyPayVisitMapper propertyPayVisitMapper,
                                       PropertyChargeVisitMapper chargeVisitMapper) {
        this.propertyPayVisitMapper = propertyPayVisitMapper;
        this.chargeVisitMapper = chargeVisitMapper;
    }

    /**
     * 根据主键删除数据
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return propertyPayVisitMapper.deleteByPrimaryKey(id);
    }

    /**
     * 全量插入
     * @param record
     * @return
     */
    @Override
    public int insert(PropertyPayVisit record) {
        return propertyPayVisitMapper.insert(record);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(PropertyPayVisit record) {
        // 修改录入信息中的缴费状态
        PropertyChargeVisit chargeVisit = new PropertyChargeVisit();
        chargeVisit.setVisitStatus("0");
        chargeVisit.setId(Integer.parseInt(record.getChargeId()));
        // 修改状态
        chargeVisitMapper.updateByPrimaryKeySelective(chargeVisit);
        // 设置缴费时间
        record.setPayDate(DateUtil.now());
        return propertyPayVisitMapper.insertSelective(record);
    }

    /**
     * 根据主键查询数据
     * @param id
     * @return
     */
    @Override
    public PropertyPayVisit selectByPrimaryKey(Integer id) {
        return propertyPayVisitMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(PropertyPayVisit record) {
        return propertyPayVisitMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新数据
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(PropertyPayVisit record) {
        return propertyPayVisitMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询数据
     * @param propertyPayVisit  查询条件
     * @param page              当前页
     * @param limit             每页显示的数据量
     * @return
     */
    @Override
    public List<PropertyPayVisit> selectDataByPage(PropertyPayVisit propertyPayVisit, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页插件处理查询结果
        PageInfo<PropertyPayVisit> pageInfo = new PageInfo<PropertyPayVisit>(propertyPayVisitMapper.selectDataByParam(propertyPayVisit));
        // 返回结果
        return pageInfo.getList();
    }

    /**
     * 查询数据总量
     * @param propertyPayVisit      查询条件
     * @return
     */
    @Override
    public Integer selectDataCount(PropertyPayVisit propertyPayVisit) {
        return propertyPayVisitMapper.selectDataCount(propertyPayVisit);
    }
}
