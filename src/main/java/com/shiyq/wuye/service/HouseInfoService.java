package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.HouseInfo;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:41
 */
public interface HouseInfoService {
    int deleteByPrimaryKey(String ids);

    int insert(HouseInfo record);

    int insertSelective(HouseInfo record);

    HouseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseInfo record);

    int updateByPrimaryKey(HouseInfo record);

    List<HouseInfo> selectDataByPage(HouseInfo houseInfo, Integer page, Integer limit);

    Integer selectDataCount(HouseInfo houseInfo);

    List<HouseInfo> selectDataAllInfo(HouseInfo houseInfo);

    int updateSoldInfo(HouseInfo houseInfo, String isHouseHolder);

    int insertFamilyInfo(HouseInfo houseInfo, String isHouseHolder);
}
