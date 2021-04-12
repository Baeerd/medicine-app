package com.app.comment.mapper;

import com.app.comment.entity.CommentVo;
import com.app.common.entity.PageModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.app.common.mapper.BaseMapper;
import com.app.comment.entity.Comment;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>{

    List<CommentVo> findComments(Map<String, String> params);

    Integer findCommentsCounts(Map<String, String> params);
}
