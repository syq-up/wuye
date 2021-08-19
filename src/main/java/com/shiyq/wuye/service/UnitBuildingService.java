package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.UnitBuilding;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:43
 */
public interface UnitBuildingService {
    int deleteByPrimaryKey(String ids);

    int insert(UnitBuilding record);

    int insertSelective(UnitBuilding record);

    UnitBuilding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnitBuilding record);

    int updateByPrimaryKey(UnitBuilding record);

    List<UnitBuilding> selectDataByPage(UnitBuilding unitBuilding, Integer page, Integer limit);

    Integer selectDataCount(UnitBuilding unitBuilding);

    List<UnitBuilding> selectAllInfo(UnitBuilding unitBuilding);

    UnitBuilding selectInfoByNum(Integer num);
}
