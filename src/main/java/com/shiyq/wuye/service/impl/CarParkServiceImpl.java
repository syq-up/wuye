package com.shiyq.wuye.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.mapper.CarParkMapper;
import com.shiyq.wuye.entity.DO.CarPark;

import java.util.List;

/**
 */
@Service
public class CarParkServiceImpl implements com.shiyq.wuye.service.CarParkService {

    private final CarParkMapper carParkMapper;

    // 注入
    public CarParkServiceImpl(CarParkMapper carParkMapper) {
        this.carParkMapper = carParkMapper;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        // 删除前查询是否有售出的
        List<CarPark> carParks = carParkMapper.selectInfoByIds(id);
        for (CarPark carPark : carParks) {
            if("1".equals(carPark.getParkState())) {
                return -500;
            }
        }
        return carParkMapper.deleteByPrimaryKey(id);
    }


    @Override
    public int insert(CarPark record) {
        return carParkMapper.insert(record);
    }


    @Override
    public int insertSelective(CarPark record) {
        return carParkMapper.insertSelective(record);
    }


    @Override
    public CarPark selectByPrimaryKey(Integer id) {
        return carParkMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(CarPark record) {
        return carParkMapper.updateByPrimaryKeySelective(record);
    }


    @Override
    public int updateByPrimaryKey(CarPark record) {
        return carParkMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询数据
     * @param carPark    查询条件
     * @param page       当前页
     * @param limit      每页显示的数据量
     * @return
     */
    @Override
    public List<CarPark> selectDataByPage(CarPark carPark, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 查询数据
        PageInfo<CarPark> pageInfo = new PageInfo<CarPark>(carParkMapper.selectDataByParam(carPark));
        // 返回分页查询后的数据
        return pageInfo.getList();
    }

    /**
     * 查询数据总数
     * @param carPark   查询条件
     * @return
     */
    @Override
    public Integer selectDataCount(CarPark carPark) {
        return carParkMapper.selectDataCount(carPark);
    }
}
