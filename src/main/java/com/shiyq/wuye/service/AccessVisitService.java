package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.AccessVisit;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:35
 */
public interface AccessVisitService {
    int deleteByPrimaryKey(String ids);

    int insertSelective(AccessVisit record);

    String insertSelectiveByScanQRCode(AccessVisit record);

    AccessVisit selectByPrimaryKey(Integer id);

    String verifyQRCodeByKey(String key);

    int updateByPrimaryKeySelective(AccessVisit record);

    List<AccessVisit> selectByParam(AccessVisit accessVisit);

    Integer selectCountByParam(AccessVisit accessVisit);

    List<AccessVisit> selectDataByPage(AccessVisit accessVisit, Integer page, Integer limit);
}
