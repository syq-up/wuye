package com.shiyq.wuye.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyq.wuye.service.AccessVisitService;
import com.shiyq.wuye.utils.QRCodeUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.entity.DO.AccessVisit;
import com.shiyq.wuye.mapper.AccessVisitMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 出入登记表业务层
 */
@Service
public class AccessVisitServiceImpl implements AccessVisitService {

    private final AccessVisitMapper accessVisitMapper;
    private final StringRedisTemplate stringRedisTemplate;

    // 注入
    public AccessVisitServiceImpl(AccessVisitMapper accessVisitMapper,
                                  StringRedisTemplate stringRedisTemplate) {
        this.accessVisitMapper = accessVisitMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private String URL = "http://127.0.0.1:9090/housing/accessVisit/scanCodeForVerification/";
//    private String URL = "https://wuye.shiyq.top/housing/accessVisit/scanCodeForVerification/";

    /**
     * 根据多主键删除
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        return accessVisitMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(AccessVisit record) {
        record.setIsResident("0");
        record.setCreateDate(DateUtil.now());
        return accessVisitMapper.insertSelective(record);
    }

    /**
     * 通过扫二维码插入记录
     * @param record
     * @return
     */
    @Override
    public String insertSelectiveByScanQRCode(AccessVisit record) {
        if (accessVisitMapper.insertSelective(record) > 0) {
            stringRedisTemplate.opsForValue()
                    .set(record.getId().toString(), record.getUserId().toString(), 5*60, TimeUnit.SECONDS);
            return URL + record.getId();
        }
        return null;
    }

    /**
     * 生成通行二维码
     * @param url
     * @return
     */
    public String getQRCodeBase64(String url) {
        return QRCodeUtil.generateQRCodeBase64(url, 300, "jpeg");
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public AccessVisit selectByPrimaryKey(Integer id) {
        return accessVisitMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Redis缓存，进行通行校验
     * @param key
     * @return
     */
    @Override
    public String verifyQRCodeByKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 选择性更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(AccessVisit record) {
        return accessVisitMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据实体类作为参数查询
     * @param accessVisit
     * @return
     */
    @Override
    public List<AccessVisit> selectByParam(AccessVisit accessVisit) {
        return accessVisitMapper.selectByParam(accessVisit);
    }

    /**
     * 根据实体类作为参数查询数据总数
     * @param accessVisit
     * @return
     */
    @Override
    public Integer selectCountByParam(AccessVisit accessVisit) {
        return accessVisitMapper.selectCountByParam(accessVisit);
    }

    /**
     * 分页查询
     * @param accessVisit
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<AccessVisit> selectDataByPage(AccessVisit accessVisit, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页插件处理数据
        PageInfo<AccessVisit> pageInfo = new PageInfo<>(accessVisitMapper.selectByParam(accessVisit));
        // 返回数据
        return pageInfo.getList();
    }

}
