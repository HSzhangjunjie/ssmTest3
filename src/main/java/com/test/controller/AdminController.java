package com.test.controller;

import com.github.pagehelper.PageInfo;
import com.test.bean.Admin;
import com.test.dao.entity.ResultEntity;
import com.test.exception.RegisterFailedException;
import com.test.service.IAdminService;
import com.test.utils.CrowdConstant;
import com.test.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.controller
 * @ClassName: AdminController
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/6 22:19
 * @Version: 1.0
 */
@Controller
public class AdminController {
    @Autowired
    private IAdminService service;

    /**
     * description: 登陆验证
     * create time: 23:53 2020/5/8
     */
    @RequestMapping("/admin/doLogin")
    public String doLogin(@RequestParam("loginAccount") String loginAccount, @RequestParam("userPassword") String userPassword, HttpSession session) {
        Admin admin = service.findAdminAccount(loginAccount, userPassword);
        session.setAttribute(CrowdConstant.LOGIN_VIEW_NAME, admin);
        return "redirect:/admin/main";
    }

    /**
     * description: 退出登录
     * create time: 23:54 2020/5/8
     */
    @RequestMapping("/admin/loginOut")
    public String doLogOut(HttpSession session) {
        // session失效
        session.invalidate();
        return "redirect:/admin/login";
    }

    @RequestMapping("/admin/pages")
    public String getPageInfo(@RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, ModelMap modelMap) {
        PageInfo<Admin> pageInfo = service.getPageInfo(keyword, pageNum, pageSize);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin/admin-user";
    }

    @RequestMapping("/admin/remove/{id}/{pageNum}")
    public String remove(@PathVariable("id") String id, @PathVariable(value = "pageNum") Integer pageNum) {
        service.remove(Integer.parseInt(id));
        return "redirect:/admin/pages?pageNum=" + pageNum;
    }

    @RequestMapping("/admin/remove/{id}")
    @ResponseBody
    public ResultEntity removeIds(@PathVariable("id") String id) {
        if (id.contains("-")) {
            String[] ids = id.split("-");
            for (String temId : ids) {
                service.remove(Integer.parseInt(temId));
            }
            return ResultEntity.successWithoutData();
        }else {
            return ResultEntity.failed("数据删除失败");
        }
    }

    @RequestMapping("/admin/save")
    public String save(Admin admin) {
        // 判断是否为空表单
        if ("".equals(admin.getLoginAccount()) || admin.getLoginAccount() == null || admin.getUserPassword() == null || "".equals(admin.getUserPassword())) {
            throw new RegisterFailedException(CrowdConstant.MESSAGE_LOGIN_NULL_INVALIDATE);
        }
        // 密码加密
        String password = admin.getUserPassword();
        password = CrowdUtils.toMd5(password);
        admin.setUserPassword(password);
        // 生成时间并保存
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        admin.setCreateTime(time);
        // 保存用户
        try {
            service.save(admin);
        } catch (Exception e) {
            throw new RegisterFailedException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
        }
        return "redirect:/admin/pages";
    }

    @RequestMapping("/admin/find/{id}")
    public String findById(@PathVariable("id") Integer id, ModelMap modelMap) {
        Admin admin = service.findById(id);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_ADMIN, admin);
        return "admin/admin-edit";
    }

    @RequestMapping("/admin/update/{id}")
    public String update(Admin admin) {
        admin.setUserPassword(CrowdUtils.toMd5(admin.getUserPassword()));
        service.update(admin);
        return "redirect:/admin/pages";
    }
}
