package com.shiyq.wuye.mapper;

import com.shiyq.wuye.entity.DO.AccessCar;
import com.shiyq.wuye.entity.DTO.AccessCarDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
@Mapper
public interface AccessCarMapper {

    int deleteByPrimaryKey(@Param("ids") String ids);

    int insertSelective(AccessCar record);

    AccessCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccessCar record);

    List<AccessCar> selectAll();

    /**
     * 根据实体类作为参数查询
     * @param accessCar
     * @return
     */
    List<AccessCarDTO> selectByParam(AccessCar accessCar);

    /**
     * 根据实体类作为参数查询数据总数
     * @param accessCar
     * @return
     */
    Integer selectCountByParam(AccessCar accessCar);

}
