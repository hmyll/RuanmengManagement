package edu.aynu.common.interceptor;

import edu.aynu.user.entity.User;
import edu.aynu.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session，判断用户信息是否存在
        User user = (User)request.getSession().getAttribute("userInfo");
        if(user == null){
            //实现自动登录，Chrome和edge有自动保存cookie的功能，测试不了，只能在ie浏览器测试
            user = autoLogin(request);
            if(user != null){
                request.getSession().setAttribute("userInfo",user);
                log.debug("放行请求" + request.getRequestURI());
                return true;
            }
            log.debug("未登录请求" + request.getRequestURI());
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        log.debug("放行请求" + request.getRequestURI());
        return true;
    }

    private User autoLogin(HttpServletRequest request) {
        String loginname = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for (Cookie cookie : cookies) {
            if("RuangMeng_LoginName".equals(cookie.getName())){
                loginname = cookie.getValue();
            }
            if("RuangMeng_Password".equals(cookie.getName())){
                password = cookie.getValue();
            }
        }
        if(loginname == null || password == null) {
            return null;
        }
        User user = new User();
        user.setLoginName(loginname);
        user.setPassword(password);
        return userService.Login(user);
    }
}
