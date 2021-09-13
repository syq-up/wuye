package com.shiyq.wuye.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyq.wuye.entity.DO.*;
import com.shiyq.wuye.mapper.*;
import com.shiyq.wuye.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service
public class UserServiceImpl implements com.shiyq.wuye.service.UserService {

    private final UserMapper userMapper;
    private final UserUnitRelationMapper unitRelationMapper;
    private final HouseInfoMapper houseInfoMapper;
    private final CarParkMapper carParkMapper;
    private final UserComplaintMapper userComplaintMapper;
    private final UserRepairMapper userRepairMapper;
    private final PropertyPayVisitMapper propertyPayVisitMapper;
    private final CommentService commentService;

    // 注入
    public UserServiceImpl(UserMapper userMapper,
                           UserUnitRelationMapper unitRelationMapper,
                           HouseInfoMapper houseInfoMapper,
                           CarParkMapper carParkMapper,
                           UserComplaintMapper userComplaintMapper,
                           UserRepairMapper userRepairMapper,
                           PropertyPayVisitMapper propertyPayVisitMapper,
                           CommentService commentService) {
        this.userMapper = userMapper;
        this.unitRelationMapper = unitRelationMapper;
        this.houseInfoMapper = houseInfoMapper;
        this.carParkMapper = carParkMapper;
        this.userComplaintMapper = userComplaintMapper;
        this.userRepairMapper = userRepairMapper;
        this.propertyPayVisitMapper = propertyPayVisitMapper;
        this.commentService = commentService;
    }

    /**
     * 根据主键删除
     * @param ids
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String ids) {
        // 查询改用户信息是否被 用户与住房信息绑定， 如果有绑定不可删除
        List<UserUnitRelation> relations = unitRelationMapper.selectInfoByIds(null, ids);
        if(relations != null && relations.size() > 0) {
            return -500;
        }
        // 查询是否有与评价表绑定的信息, 如果有不可以删除
        List<Comment> comments = commentService.selectInfoByUserId(ids);
        if(comments != null && comments.size() > 0) {
            return -500;
        }
        return userMapper.deleteByPrimaryKey(ids);
    }

    /**
     * 全量插入
     * @param record
     * @return
     */
    @Override
    public int insert(User record) {
        // 判断如果身份证号不为空则查询身份证号信息
        if(!StrUtil.isBlank(record.getCardNum())) {
            // 使用Hutool工具类解析身份证
            // 户籍信息
            record.setRegisterAddress(IdcardUtil.getProvinceByIdCard(record.getCardNum()));
            // 性别
            record.setSex(IdcardUtil.getGenderByIdCard(record.getCardNum()) == 0 ? "女" : "男");
        }
        return userMapper.insert(record);
    }

    /**
     * 选择性插入
     * @param record
     * @return
     */
    @Override
    public int insertSelective(User record) {
        // 判断如果身份证号不为空则查询身份证号信息
        if(!StrUtil.isBlank(record.getCardNum())) {
            // 使用Hutool工具类解析身份证
            // 户籍信息
            record.setRegisterAddress(IdcardUtil.getProvinceByIdCard(record.getCardNum()));
            // 性别
            record.setSex(IdcardUtil.getGenderByIdCard(record.getCardNum()) == 0 ? "女" : "男");
        }
        // 查询插入的用户信息是否重复
        User selectParam = new User();
        selectParam.setUserName(record.getUserName());
        selectParam.setPhone(record.getPhone());
        List<User> users = userMapper.selectDataByParam(selectParam);
        if(users != null && users.size() > 0) {
            return -500;
        }
        return userMapper.insertSelective(record);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键选择性更新
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(User record) {
        // 判断如果身份证号不为空则查询身份证号信息
        if(!StrUtil.isBlank(record.getCardNum())) {
            // 使用Hutool工具类解析身份证
            // 户籍信息
            record.setRegisterAddress(IdcardUtil.getProvinceByIdCard(record.getCardNum()));
            // 性别
            record.setSex(IdcardUtil.getGenderByIdCard(record.getCardNum()) == 0 ? "女" : "男");
        }
        // 更新前将相关数据更新
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setUserId(record.getId());
        houseInfo.setOwnerName(record.getUserName());
        houseInfo.setPhone(record.getPhone());
        houseInfoMapper.updateInfoByUserId(houseInfo);
        List<CarPark> carPark = carParkMapper.selectInfoByUserName(record.getUserName());
        if(carPark != null && carPark.size() > 0) {
            for (CarPark park : carPark) {
                park.setOwnerName(record.getUserName());
                park.setPhone(record.getPhone());
                // 调用更新方法
                carParkMapper.updateByPrimaryKeySelective(park);
            }
        }
        UserComplaint userComplaint = new UserComplaint();
        userComplaint.setUserId(record.getId());
        userComplaint.setUserName(record.getUserName());
        userComplaint.setPhone(record.getPhone());
        userComplaintMapper.updateInfoByUserId(userComplaint);
        UserRepair userRepair = new UserRepair();
        userRepair.setUserId(record.getId());
        userRepair.setUserName(record.getUserName());
        userRepair.setPhone(record.getPhone());
        userRepairMapper.updateInfoByUserId(userRepair);
        List<PropertyPayVisit> propertyPayVisit = propertyPayVisitMapper.selectInfoByUserName(record.getUserName());
        if(propertyPayVisit != null && propertyPayVisit.size() > 0) {
            for (PropertyPayVisit payVisit : propertyPayVisit) {
                payVisit.setClientName(record.getUserName());
                propertyPayVisitMapper.updateByPrimaryKeySelective(payVisit);
            }
        }

        return userMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键更新信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(User record) {
        // 判断如果身份证号不为空则查询身份证号信息
        if(!StrUtil.isBlank(record.getCardNum())) {
            // 使用Hutool工具类解析身份证
            // 户籍信息
            record.setRegisterAddress(IdcardUtil.getProvinceByIdCard(record.getCardNum()));
            // 性别
            record.setSex(IdcardUtil.getGenderByIdCard(record.getCardNum()) == 0 ? "女" : "男");
        }
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询数据
     * @param user  查询条件
     * @param page  当前页
     * @param limit 每页显示条数
     * @return
     */
    @Override
    public List<User> selectDataByPage(User user, Integer page, Integer limit) {
        // 开启分页插件
        PageHelper.startPage(page, limit);
        // 使用分页工具查询
        PageInfo<User> pageInfo = new PageInfo<User>(userMapper.selectDataByParam(user));
        // 返回数据
        return pageInfo.getList();
    }

    /**
     * 查询数据总量
     * @param user
     * @return
     */
    @Override
    public Integer selectDataCount(User user) {
        return userMapper.selectDataCount(user);
    }

    /**
     * 根据条件查询
     * @param user
     * @return
     */
    @Override
    public List<User> selectDataByParam(User user) {
        return userMapper.selectDataByParam(user);
    }

    /**
     * 前台登陆
     * @param user
     * @return
     */
    @Override
    public User selectLoginByParam(User user) {
        return userMapper.selectLoginByParam(user);
    }
}
