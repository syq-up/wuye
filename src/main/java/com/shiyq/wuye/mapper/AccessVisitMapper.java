package com.shiyq.wuye.mapper;

import com.shiyq.wuye.entity.DO.AccessVisit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
@Mapper
public interface AccessVisitMapper {
    int deleteByPrimaryKey(@Param("ids") String ids);

    int insertSelective(AccessVisit record);

    AccessVisit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccessVisit record);

    /**
     * 根据实体类作为参数查询
     * @param accessVisit
     * @return
     */
    List<AccessVisit> selectByParam(AccessVisit accessVisit);

    /**
     * 根据实体类作为参数查询数据总数
     * @param accessVisit
     * @return
     */
    Integer selectCountByParam(AccessVisit accessVisit);
}
