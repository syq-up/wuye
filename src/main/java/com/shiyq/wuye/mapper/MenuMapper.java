package com.shiyq.wuye.mapper;

import com.shiyq.wuye.entity.DO.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 */
@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectByAllInfo();
}
