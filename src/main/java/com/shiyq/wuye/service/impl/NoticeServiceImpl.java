package com.shiyq.wuye.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.mapper.NoticeMapper;
import com.shiyq.wuye.entity.DO.Notice;

import java.util.List;

/**
 * 对应数据库公告表的业务层
 */
@Service
public class NoticeServiceImpl implements com.shiyq.wuye.service.NoticeService {

    private final NoticeMapper noticeMapper;

    // 注入
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public int deleteByPrimaryKey(String ids) {
        return noticeMapper.deleteByPrimaryKey(ids);
    }


    @Override
    public int insertSelective(Notice record) {
        return noticeMapper.insertSelective(record);
    }


    @Override
    public Notice selectByPrimaryKey(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(Notice record) {
        return noticeMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 分页查询数据
     * @param notice
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Notice> selectDataByPage(Notice notice, Integer page, Integer limit) {
        // 开启分页
        PageHelper.startPage(page, limit);
        PageInfo<Notice> pageInfo = new PageInfo<Notice>(noticeMapper.selectDataByParam(notice));
        return pageInfo.getList();
    }

    /**
     * 查询数据总条数
     * @param notice
     * @return
     */
    @Override
    public Integer selectDataCount(Notice notice) {
        return noticeMapper.selectCount(notice);
    }
}
