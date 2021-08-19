package com.shiyq.wuye.service;

import com.shiyq.wuye.entity.DO.Comment;

import java.util.List;

/**
 * @author shiyq
 * @create 2021-06-09 18:40
 */
public interface CommentService {
    int deleteByPrimaryKey(String ids);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    List<Comment> selectDataByPage(Comment comment, Integer page, Integer limit);

    Integer selectDataCount(Comment comment);

    List<Comment> selectInfoByUserId(String ids);

    Integer updateByDelete(String delIds, String type);

    List<Comment> selectInfoByNoticeId(Integer noticeId);
}
