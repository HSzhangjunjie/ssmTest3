package com.test.config;

import com.google.gson.Gson;
import com.test.dao.entity.ResultEntity;
import com.test.exception.AccessForbiddenException;
import com.test.exception.LoginFailedException;
import com.test.exception.RegisterFailedException;
import com.test.utils.CrowdConstant;
import com.test.utils.CrowdUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.config
 * @ClassName: CrowdExceptionResolver
 * @Author: ZhangJunjie
 * @Description: 基于注解的异常处理器类
 * @Date: 2020/5/7 17:02
 * @Version: 1.0
 */
@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * description: 建立登陆失败异常映射关系
     */
    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) {
        return commonException(CrowdConstant.LOGIN_EXCEPTION_VIEW_NAME, exception, request, response);
    }

    /**
     * description: 建立注册失败异常映射关系
     */
    @ExceptionHandler(RegisterFailedException.class)
    public ModelAndView resolveRegisterFailedException(RegisterFailedException exception, HttpServletRequest request, HttpServletResponse response) {
        return commonException(CrowdConstant.ADD_EXCEPTION_VIEW_NAME, exception, request, response);
    }

    /**
     * description: 建立登陆失败异常映射关系
     */
    @ExceptionHandler(AccessForbiddenException.class)
    public ModelAndView resolveAccessForbiddenException(AccessForbiddenException exception, HttpServletRequest request, HttpServletResponse response) {
        return commonException(CrowdConstant.EXCEPTION_VIEW_NAME, exception, request, response);
    }

    /**
     * description: 异常整合类
     * create time: 17:05 2020/5/7
     */
    private ModelAndView commonException(String viewName, Exception exception, HttpServletRequest request, HttpServletResponse response) {
        //判断是否是Ajax请求
        if (CrowdUtils.isJson(request)) {
            //创建Gson对象
            Gson gson = new Gson();
            try {
                response.getWriter().write(gson.toJson(ResultEntity.failed(exception.getMessage())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);
            modelAndView.setViewName(viewName);
            return modelAndView;
        }
    }
}
