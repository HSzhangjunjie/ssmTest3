package com.test.controller;

import com.test.bean.Role;
import com.test.dao.entity.ResultEntity;
import com.test.service.IAdminService;
import com.test.service.IAuthService;
import com.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.controller
 * @ClassName: AssignController
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/17 12:27
 * @Version: 1.0
 */
@Controller
public class AssignController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IAuthService authService;

    @RequestMapping("/assign/roles")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        // 查找已分配的角色
        List<Role> assignedRoles = roleService.getAssignedRole(adminId);
        // 查询未分配的角色
        List<Role> unassignedRoles = roleService.getUnassignedRole(adminId);
        // 存入模型
        modelMap.addAttribute("assignedRoles", assignedRoles);
        modelMap.addAttribute("unassignedRoles", unassignedRoles);
        return "assign/assign-role";
    }

    @RequestMapping("/assign/doAssign")
    public String doAssignRoles(@RequestParam("adminId") Integer adminId, @RequestParam(value = "assignedRoles", required = false) List<Integer> assignedRoles) {
        adminService.doAssignRoles(adminId, assignedRoles);

        return "redirect:/admin/pages";
    }

    @RequestMapping(value = "/assign/authIds/{roleId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Integer>> getAuthsId(@PathVariable Integer roleId) {
        List<Integer> ids=authService.getAuthsIdByRoleId(roleId);
        return ResultEntity.successWithData(ids);
    }



    @RequestMapping(value = "/assign/authIds", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveAssignedAuths(@RequestBody Map<String,List<Integer>> map) {
        authService.saveAssignedAuths(map);
        return ResultEntity.successWithoutData();
    }
}
