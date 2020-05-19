package com.test.interceptor;

import com.test.bean.Admin;
import com.test.exception.AccessForbiddenException;
import com.test.utils.CrowdConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.interceptor
 * @ClassName: LoginInterceptor
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/9 0:29
 * @Version: 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 通过request获取Session
        HttpSession session = request.getSession();
        // 尝试获取Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.LOGIN_VIEW_NAME);
        // 如果未登录拒绝访问，抛出异常
        if (admin == null) {
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ASSESS_FORBID);
        } else {
            return true;
        }

    }
}
