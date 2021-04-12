package com.app.comment.controller;

import com.app.comment.entity.CommentVo;
import com.app.comment.service.CommentService;
import com.app.common.entity.PageModel;
import com.app.common.util.Util;
import com.app.goods.entity.Goods;
import com.app.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.comment.entity.Comment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/comment")
public class CommentController extends BaseController<Comment>{

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/commentList")
    public ModelAndView commentList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/comment/commentList");
        String jsonFromRequest = super.getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(jsonFromRequest);

        Map<String, String> commentParams = new HashMap<>();
        List<Goods> goods = null;
        // 如果商品信息参数是空的，说明是从评论管理菜单进入的，此时不查询商品信息，并且查询所有评论信息
        if(params.get("goodsId") != null || params.get("goodsName") != null) {
            // 查询商品详细信息
            params.put("id", params.get("goodsId"));
            params.put("name", params.get("goodsName"));
            goods = goodsService.findByParam(params);
        }
        if(goods != null && goods.size() > 0) {
            modelAndView.addObject("goods", goods.get(0));
            commentParams.put("goodsId", goods.get(0).getId().toString());
        }

        // 查询商品对应的所有评论列表
        PageModel<Comment> page = commentService.findByPage(commentParams);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @RequestMapping("/comments")
    public ModelAndView comments(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/comment/comments");
        String jsonFromRequest = super.getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(jsonFromRequest);

        // 查询商品对应的所有评论列表
        PageModel<CommentVo> page = commentService.findComments(params);
        modelAndView.addObject("page", page);
        modelAndView.addObject("paramsGoodsName", params.get("goodsName"));
        return modelAndView;
    }

}
