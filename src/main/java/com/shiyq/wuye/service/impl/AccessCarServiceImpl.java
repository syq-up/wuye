package com.shiyq.wuye.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyq.wuye.entity.DO.AccessCar;
import com.shiyq.wuye.entity.DTO.AccessCarDTO;
import com.shiyq.wuye.entity.DTO.LicencePlateDTO;
import com.shiyq.wuye.entity.DTO.ResultMessage;
import com.shiyq.wuye.mapper.AccessCarMapper;
import com.shiyq.wuye.mapper.CarParkMapper;
import com.shiyq.wuye.service.AccessCarService;
import com.shiyq.wuye.utils.AipUtil;
import com.shiyq.wuye.utils.Base64Util;
import com.shiyq.wuye.utils.HttpUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.List;

/**
 */
@Service
public class AccessCarServiceImpl implements AccessCarService {

    private final AccessCarMapper accessCarMapper;
    private final CarParkMapper carParkMapper;
    private final StringRedisTemplate stringRedisTemplate;

    // 注入
    public AccessCarServiceImpl(AccessCarMapper accessCarMapper,
                                CarParkMapper carParkMapper,
                                StringRedisTemplate stringRedisTemplate) {
        this.accessCarMapper = accessCarMapper;
        this.carParkMapper = carParkMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 根据多主键删除
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        return accessCarMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(AccessCar record) {
        record.setRegistered("0");
        record.setCreateTime(DateUtil.now());
        return accessCarMapper.insertSelective(record);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public ResultMessage insertSelectiveByScan(AccessCar record, MultipartFile file) {
        // 获取车牌号
        String licencePlate = scanLicensePlate(file);
        // 判断车牌号是否已经在redis缓存中
        if (!stringRedisTemplate.hasKey("MangoCommunity")) {
            // 查询所有已登记的车辆，存入redis
            List<String> list = carParkMapper.selectAllLicensePlate();
            for (String licencePlateItem : list) {
                stringRedisTemplate.opsForSet().add("MangoCommunity", licencePlateItem);
            }
        }
        // 判断车辆是否已在小区内登记
        boolean passed = stringRedisTemplate.opsForSet().isMember("MangoCommunity", licencePlate);
        if (passed) {
            // 车牌号、车辆是否已登记、创建时间、创建者
            record.setLicencePlate(licencePlate).setRegistered("1").setCreateTime(DateUtil.now()).setCreateBy(0);
            accessCarMapper.insertSelective(record);
            return new ResultMessage(0, "当前车辆：【"+licencePlate+"】出入记录成功。");
        }
        return new ResultMessage(207, "当前车辆：【"+licencePlate+"】未在小区登记！");
    }

    /**
     * 车牌扫描
     * @param file 车牌抓拍照片
     * @return 车牌号
     */
    public String scanLicensePlate(MultipartFile file) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/webimage";
        try {
            // 文件转base64字符串，并使用utf-8进行编码
            byte[] imgData = file.getBytes();
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            // 接口需要的参数
            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AipUtil.getAuth();// [调用鉴权接口获取的token]
            // 调用车牌识别接口，获取车牌号json字符串
            String result = HttpUtils.post(url, accessToken, param);
            // 将车牌号信息转化为对象
            LicencePlateDTO licencePlateDTO = JSON.parseObject(result, LicencePlateDTO.class);
            // 返回车牌号
            return licencePlateDTO.getWords_result().get(0).get("words");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据实体类作为参数查询数据总数
     * @param accessCar
     * @return
     */
    @Override
    public Integer selectCountByParam(AccessCar accessCar) {
        return accessCarMapper.selectCountByParam(accessCar);
    }

    /**
     * 分页查询
     * @param accessCar
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<AccessCarDTO> selectDataByPage(AccessCar accessCar, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页插件处理数据
        PageInfo<AccessCarDTO> pageInfo = new PageInfo<>(accessCarMapper.selectByParam(accessCar));
        // 返回数据
        return pageInfo.getList();
    }

}
