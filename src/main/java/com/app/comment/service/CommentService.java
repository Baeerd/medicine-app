package com.app.comment.service;

import com.app.comment.entity.CommentVo;
import com.app.common.entity.PageModel;
import com.app.common.service.BaseService;
import com.app.comment.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService extends BaseService<Comment>{

    PageModel<CommentVo> findComments(Map<String, String> params);
}
