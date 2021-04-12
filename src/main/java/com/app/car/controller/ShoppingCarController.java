package com.app.car.controller;

import com.app.car.service.ShoppingCarService;
import com.app.common.entity.PageModel;
import com.app.common.entity.Response;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import com.app.common.controller.BaseController;
import com.app.car.entity.ShoppingCar;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Scope("prototype")
@RequestMapping("/shoppingCar")
public class ShoppingCarController extends BaseController<ShoppingCar>{

    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加至购物车
     * @return
     */
    @RequestMapping("/addGoods")
    public Response addGoods(HttpServletRequest request) {
        String json = super.getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        shoppingCarService.addGoods(params);
        return new Response().success();
    }

    /**
     * 购物车列表（查询当前登录人的）
     * @return
     */
    @RequestMapping("carList")
    public ModelAndView carList() {
        ModelAndView modelAndView = new ModelAndView("/car/carList");
        Map<String, String> params = new HashMap<>();
        params.put("userId", LoginUtil.getUserId());
        PageModel<ShoppingCar> page = shoppingCarService.findByPage(params);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    /**
     * 购物车数量-1
     * @return
     */
    @RequestMapping("/minusCarNum")
    public Response minusCarNum(HttpServletRequest request) {
        String id = request.getParameter("id");// 购物车id
        String goodsId = request.getParameter("goodsId");// 购物车中商品id
        // 商品表库存+1
        goodsService.updateGoodsNum(goodsId, "+1");
        // 购物车此商品数量-1
        shoppingCarService.updateCarNum(id, "-1");
        return new Response().success();
    }

    /**
     * 购物车数量+1
     * @return
     */
    @RequestMapping("/plusCarNum")
    public Response plusCarNum(HttpServletRequest request) {
        String id = request.getParameter("id");// 购物车id
        String goodsId = request.getParameter("goodsId");// 购物车中商品id
        // 商品表库存-1
        goodsService.updateGoodsNum(goodsId, "-1");
        // 购物车此商品数量+1
        shoppingCarService.updateCarNum(id, "+1");
        return new Response().success();
    }

    /**
     * 清空购物车
     * @return
     */
    @RequestMapping("/clearCar")
    public Response clearCar() {
        shoppingCarService.deleteByUser(LoginUtil.getUserId());
        return new Response().success();
    }
    
    /**
     * 	提交订单
     * @return
     */
    @RequestMapping("/addOrder")
    public Response addOrder() {
    	shoppingCarService.addOrderByUser(LoginUtil.getUserId());
    	return new Response().success();
    }

}
