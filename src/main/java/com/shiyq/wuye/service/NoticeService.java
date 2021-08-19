package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.Notice;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:42
 */
public interface NoticeService {
    int deleteByPrimaryKey(String ids);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    List<Notice> selectDataByPage(Notice notice, Integer page, Integer limit);

    Integer selectDataCount(Notice notice);
}
