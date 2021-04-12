package com.app.user.controller;

import com.app.car.entity.ShoppingCar;
import com.app.car.service.ShoppingCarService;
import com.app.common.entity.PageModel;
import com.app.common.controller.BaseController;
import com.app.common.entity.AppConfig;
import com.app.common.entity.Response;
import com.app.common.exception.MessageException;
import com.app.common.util.LoginUtil;
import com.app.common.util.Util;
import com.app.order.entity.GoodsOrder;
import com.app.order.service.GoodsOrderService;
import com.app.shops.entity.ShopsGoodsVo;
import com.app.shops.service.ShopsService;
import com.app.user.entity.User;
import com.app.user.service.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册登陆相关操作Controller
 */
@RequestMapping("/system")
@Controller
public class UserController extends BaseController<User>{

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCarService shoppingCarService;
    
    @Autowired
    private ShopsService shopsService;

    @Autowired
    private GoodsOrderService goodsOrderService;

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String json = getJsonFromRequest(request);
        Map<String, String> params = Util.jsonToMap(json);
        // 根据用户名密码查询数据库
        try {
            User user = userService.validateLogin(params.get("username"), params.get("password"));
            // 缓存用户信息
            LoginUtil.login(user);
            // 向session中放入用户信息
            request.getSession().setAttribute(LoginUtil.LOGINUSER, user);
            // 向session中放入购物车信息
            Map<String, String> carParams = new HashMap<>();
            carParams.put("userId", LoginUtil.getUserId());
            List<ShoppingCar> cars = shoppingCarService.findByParam(carParams);
            request.getSession().setAttribute("cars", cars);
            // 向session中放入订单信息
            Map<String, String> orderParams = new HashMap<>();
            carParams.put("userId", LoginUtil.getUserId());
            List<GoodsOrder> orders = goodsOrderService.findByParam(orderParams);
            request.getSession().setAttribute("orders", orders);
            // 向session中放入最新的商铺商品信息
            // 加载商铺商品信息
            List<ShopsGoodsVo> sgvList = shopsService.findNewShopsVal(4);
            request.getSession().setAttribute("sgvList", sgvList);
            response.sendRedirect(LoginUtil.getInterceptorPath());
        } catch (MessageException e) {
            request.setAttribute("error", e.getMessage());
            response.sendRedirect(LoginUtil.LOGINPAGE);
        }
    }

    @RequestMapping("/regist")
    public ModelAndView regist(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        String json = this.getJsonFromRequest(request);
        User user = Util.jsonToBean(json, User.class);
        mv.addObject(user);

        //用户名或密码为空，注册失败
        if(StringUtil.isEmpty(user.getUsername())||StringUtil.isEmpty(user.getPassword())){
            mv.setViewName("regist");
            mv.addObject("msg","注册失败！请输入用户名密码");
            return mv;
        }

        //已存在的用户名，注册失败
        Map<String,String> params = new HashMap<>();
        params.put("username",user.getUsername());
        List<User> userList = userService.findByParam(params);
        if(!userList.isEmpty()){
            mv.setViewName("/regist");
            mv.addObject("msg","注册失败！此用户名已存在");
            return mv;
        }


        user.setCreatedBy("NA");
        user.setCreatedDt(new Date());
        user.setUserType("0");
        userService.insert(user);
        mv.setViewName("/login");
        mv.addObject("msg","注册成功，请登录！");
        return mv;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/login");
        request.getSession().invalidate();;
        return modelAndView;
    }



    @RequestMapping("/registVip")
    public ModelAndView registVip(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("/user/registVip");
        Map<String,String> params = new HashMap<>();
        params.put("id",request.getParameter("id"));
        User user = userService.findByParam(params).get(0);
        mv.addObject(user);
        return mv;

    }

    @RequestMapping("/registVipMain")
    public ModelAndView registVipMain(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("/user/registVip");
        String json = this.getJsonFromRequest(request);
        User user = Util.jsonToBean(json, User.class);
        mv.addObject(user);

        if(StringUtil.isEmpty(user.getPhone())){
            //手机号不能为空
            mv.addObject("msg","注册会员信息中手机号不能为空！");
            return mv;
        }
        Map<String,String> params = new HashMap<>();
        params.put("phone",user.getPhone());
        List<User> userList = userService.findByParam(params);
        if (userList==null||userList.isEmpty()){
            //数据库中无此手机号，允许注册
            user.setUserType("3");//设置用户类型为会员待审核
            userService.update(user);
            mv.addObject("msg","注册会员信息已成功提交，请耐心等待审核！");
            return mv;
        }else{
            mv.addObject("msg",user.getPhone()+" 此手机号已被注册，请查证！");
            return mv;
        }

    }


    @RequestMapping("/registSeller")
    public ModelAndView registSeller(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("/user/registSeller");
        Map<String,String> params = new HashMap<>();
        params.put("id",request.getParameter("id"));
        User user = userService.findByParam(params).get(0);
        mv.addObject(user);
        return mv;

    }

    @RequestMapping("/registSellerMain")
    public ModelAndView registSellerMain(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("/user/registSeller");
        String json = this.getJsonFromRequest(request);
        User user = Util.jsonToBean(json, User.class);
        mv.addObject(user);

        if(StringUtil.isEmpty(user.getPhone())){
            //手机号不能为空
            mv.addObject("msg","注册商家信息中手机号不能为空！");
            return mv;
        }
        Map<String,String> params = new HashMap<>();
        params.put("phone",user.getPhone());
        List<User> userList = userService.findByParam(params);
        if (userList==null||userList.isEmpty()){
            //数据库中无此手机号，允许注册
            user.setUserType("1");//设置用户类型为商家待审核
            userService.update(user);
            mv.addObject("msg","注册商家信息已成功提交，请耐心等待审核！");
            return mv;
        }else{
            mv.addObject("msg",user.getPhone()+" 此手机号已被注册，请查证！");
            return mv;
        }

    }


    @RequestMapping("/userList")
    public ModelAndView userList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/user/userList");
        Map<String, String> params = new HashMap<>();
        if(request != null) {
            String json = super.getJsonFromRequest(request);
            params = Util.jsonToMap(json);
        }
        PageModel<User> page = userService.findByPage(params);
        modelAndView.addObject("page", page);
        modelAndView.addObject("userName", params.get("username"));
        return modelAndView;
    }

    @RequestMapping("/auditUser")
    public Response auditUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String userType = request.getParameter("userType");
        userService.auditUser(Long.valueOf(id),userType);
        return new Response().success();
    }

}
