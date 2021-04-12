package com.app.common.interceptor;

import com.app.car.entity.ShoppingCar;
import com.app.car.service.ShoppingCarService;
import com.app.common.entity.AppConfig;
import com.app.common.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private AppConfig appConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object user = request.getSession().getAttribute(LoginUtil.LOGINUSER);
        if (user == null) {
            String requestUrl = request.getContextPath()+request.getServletPath();
            if("/regist.html".equals(requestUrl)||requestUrl.startsWith("/system")){
                return true;
            }
            log.info("当前请求没有登录...请求url为： "+ requestUrl);
            if(!"/error".equals(requestUrl)) {
                LoginUtil.setInterceptorPath(requestUrl);
            }
            response.sendRedirect(LoginUtil.LOGINPAGE);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
