package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.AccessCar;
import com.shiyq.wuye.entity.DTO.AccessCarDTO;
import com.shiyq.wuye.entity.DTO.ResultMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:30
 */
public interface AccessCarService {
    int deleteByPrimaryKey(String ids);

    int insertSelective(AccessCar record);

    ResultMessage insertSelectiveByScan(AccessCar record, MultipartFile file);

    Integer selectCountByParam(AccessCar accessCar);

    List<AccessCarDTO> selectDataByPage(AccessCar accessCar, Integer page, Integer limit);
}
