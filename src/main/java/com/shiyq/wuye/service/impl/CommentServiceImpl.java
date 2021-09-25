package com.shiyq.wuye.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyq.wuye.entity.DO.Comment;
import org.springframework.stereotype.Service;
import com.shiyq.wuye.mapper.CommentMapper;

import java.util.List;

/**
 */
@Service
public class CommentServiceImpl implements com.shiyq.wuye.service.CommentService {

    private final CommentMapper commentMapper;

    // 注入
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public int deleteByPrimaryKey(String ids) {
        return commentMapper.deleteByPrimaryKey(ids);
    }


    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }


    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 分页查询数据
     * @param comment
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Comment> selectDataByPage(Comment comment, Integer page, Integer limit) {
        // 开启分页
        PageHelper.startPage(page, limit);
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(commentMapper.selectDataByParam(comment));
        // System.out.println(pageInfo.getList());
        return pageInfo.getList();
    }

    /**
     * 查询数据总条数
     * @param comment
     * @return
     */
    @Override
    public Integer selectDataCount(Comment comment) {
        return commentMapper.selectCount(comment);
    }

    /**
     * 根据用户ID查询
     * @param ids
     * @return
     */
    @Override
    public List<Comment> selectInfoByUserId(String ids) {
        return commentMapper.selectInfoByUserId(ids);
    }

    /**
     * 逻辑删除
     * @param delIds
     * @return
     */
    @Override
    public Integer updateByDelete(String delIds, String type) {
        return commentMapper.updateByDelete(delIds, type);
    }

    /**
     * 根据公告ID查询
     * @param noticeId
     * @return
     */
    @Override
    public List<Comment> selectInfoByNoticeId(Integer noticeId) {
        return commentMapper.selectInfoByNoticeId(noticeId);
    }
}
