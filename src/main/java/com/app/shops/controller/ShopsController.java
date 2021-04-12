package com.app.shops.controller;

import com.app.common.entity.PageModel;
import com.app.common.exception.MessageException;
import com.app.common.util.BeanUtils;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.shops.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.shops.entity.Shops;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/shops")
public class ShopsController extends BaseController<Shops>{

    @Autowired
    private ShopsService shopsService;

    @RequestMapping("/shopsListPanel")
    public ModelAndView shopsListPanel(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/shops/shopsListPanel");
        Map<String, String> params = new HashMap<>();
        if(request != null) {
            String json = super.getJsonFromRequest(request);
            params = Util.jsonToMap(json);
        }
        PageModel<Shops> page = shopsService.findByPage(params);
        modelAndView.addObject("page", page);
        modelAndView.addObject("shopsName", params.get("name"));
        return modelAndView;
    }

    @RequestMapping("/addShops")
    public ModelAndView addShops(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        try {
            Shops shops = new Shops();
            BeanUtils.populate(request, shops);//前台传过来的参数映射成实体用于更新或新增至数据库
            // 设置文件名(模块名+时间)
            String fileName = "shops" + Util.getCurrentTime() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String imageUrl = "/image/shops/" + fileName;
            // 设置文件路径
            String destFileName = request.getSession().getServletContext().getRealPath("") + imageUrl;
            // 第一次运行的时候，创建文件路径
            File destFile = new File(destFileName);
            // 上传文件
            shopsService.uploadFile(file, destFile);
            shops.setImage(imageUrl);
            // 添加或更新至数据库
            if(shops.getId() != null) {
                shopsService.update(shops);
            } else {
                shops.setCreatedBy(LoginUtil.getUserName());
                shopsService.insert(shops);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageException("上传失败," + e.getMessage());
        }

        return showShops(request.getParameter("userId"));
    }

    /**
     * 回显当前用户下所有商铺
     * @param userId
     * @return
     */
    public ModelAndView showShops(String userId) {
        ModelAndView modelAndView = new ModelAndView("/shops/shopsListPanel");
        Map<String, String> params = new HashMap<>();
        params.put("userId",userId);
        PageModel<Shops> page = shopsService.findByPage(params);
        modelAndView.addObject("page", page);
        return modelAndView;
    }
    
}
