package com.app.goods.controller;

import com.app.common.entity.Constant;
import com.app.common.entity.PageModel;
import com.app.common.exception.MessageException;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.goods.entity.Goods;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods>{

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/goodsList")
    public ModelAndView goodsList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/goods/goodsList");
        Map<String, String> params = new HashMap<>();
        if(request != null) {
            String json = super.getJsonFromRequest(request);
            params = Util.jsonToMap(json);
        }
        PageModel<Goods> page = goodsService.findByPage(params);
        modelAndView.addObject("page", page);
        modelAndView.addObject("goodsName", params.get("name"));
        return modelAndView;
    }

    @RequestMapping("/goodsListPanel")
    public ModelAndView goodsListPanel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/goods/goodsListPanel");
        Map<String, String> params = new HashMap<>();
        if(request != null) {
            String json = super.getJsonFromRequest(request);
            params = Util.jsonToMap(json);
        }
        PageModel<Goods> page = goodsService.findByPage(params);
        modelAndView.addObject("page", page);
        modelAndView.addObject("goodsName", params.get("name"));
        return modelAndView;
    }

    @RequestMapping("/addGoods")
    public ModelAndView addGoods(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            Goods goods = new Goods();
            BeanUtils.populate(request, goods);//前台传过来的参数映射成实体用于更新或新增至数据库
            // 设置文件名(模块名+时间)
            String fileName = "goods" + Util.getCurrentTime() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String imageUrl = "/image/goods/" + fileName;
            // 设置文件路径
            String destFileName = request.getSession().getServletContext().getRealPath("") + imageUrl;
            // 第一次运行的时候，创建文件路径
            File destFile = new File(destFileName);
            // 上传文件
            goodsService.uploadFile(file, destFile);
            goods.setImage(imageUrl);
            // 添加或更新至数据库
            if(goods.getId() != null) {
                goodsService.update(goods);
            } else {
                goods.setCreatedBy(LoginUtil.getUserName());
                goodsService.insert(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("上传失败," + e.getMessage());
        }
        return goodsList(null);
    }
}
