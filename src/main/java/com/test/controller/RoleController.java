package com.test.controller;

import com.github.pagehelper.PageInfo;
import com.test.bean.Role;
import com.test.dao.entity.ResultEntity;
import com.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.controller
 * @ClassName: RoleController
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/14 19:25
 * @Version: 1.0
 */
@Controller
public class RoleController {
    @Autowired
    private IRoleService service;

    @RequestMapping("/admin/rolePages")
    @ResponseBody
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        // 如果出现了异常 就交给异常处理机制处理
        PageInfo<Role> pageInfo=service.findByKeyword(pageNum, pageSize, keyword);
        return ResultEntity.successWithData(pageInfo);
    }

    @RequestMapping(value = "/admin/roles/{roleId}", method = RequestMethod.PUT)
    @ResponseBody
    public ResultEntity updateRole(@PathVariable Integer roleId, Role role) {
        try {
            service.updateRole(roleId, role);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed("该角色名称已存在");
        }
    }

    @RequestMapping(value = "/admin/roles", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addRole(Role role) {
        try {
            service.addRole(role);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed("该角色名称已存在");
        }
    }

    @RequestMapping(value = "/admin/roles/{roleId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity updateRole(@PathVariable String roleId) {
        try {
            if (roleId.contains("-")) {
                String[] ids = roleId.split("-");
                for (String id : ids) {
                    service.deleteRole(Integer.parseInt(id));
                }
            } else {
                service.deleteRole(Integer.parseInt(roleId));
            }
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed(e.getMessage());
        }
    }
}
