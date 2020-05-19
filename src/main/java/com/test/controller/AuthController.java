package com.test.controller;

import com.test.bean.Auth;
import com.test.dao.entity.ResultEntity;
import com.test.service.IAdminService;
import com.test.service.IAuthService;
import com.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.controller
 * @ClassName: AuthController
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/17 17:02
 * @Version: 1.0
 */
@Controller
public class AuthController {
    @Autowired
    private IAuthService authService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/assign/auths", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Auth>> getAuths() {
        return ResultEntity.successWithData(authService.getAuths());
    }

}
