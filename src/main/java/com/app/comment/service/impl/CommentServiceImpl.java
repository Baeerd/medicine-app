package com.app.comment.service.impl;

import com.app.comment.entity.CommentVo;
import com.app.common.entity.PageModel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.common.service.impl.BaseServiceImpl;
import com.app.comment.entity.Comment;
import com.app.comment.mapper.CommentMapper;
import com.app.comment.service.CommentService;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PageModel<CommentVo> findComments(Map<String, String> params) {
        params = filterParams(params);
        Integer total = commentMapper.findCommentsCounts(params);
        Page<CommentVo> page = PageHelper.startPage(Integer.valueOf(params.get("pageNum")), Integer.valueOf(params.get("pageSize")));
        commentMapper.findComments(params);
        PageModel<CommentVo> result = PageModel.build(page,params);
        result.setTotal(total);
        return result;
    }
}
