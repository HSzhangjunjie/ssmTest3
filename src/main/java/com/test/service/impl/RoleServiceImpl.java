package com.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bean.Role;
import com.test.dao.mapper.RoleMapper;
import com.test.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service.impl
 * @ClassName: RoleServiceImpl
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/11 20:01
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper mapper;


    @Override
    public PageInfo<Role> findByKeyword(Integer pageNum, Integer pageSize, String keyword) {
        // 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询
        List<Role> roles = mapper.selectRoleByKeyWord(keyword);
        // 封装成PageInfo对象
        return new PageInfo<>(roles);
    }

    @Override
    public void addRole(Role role) {
        mapper.insertSelective(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return mapper.selectByPrimaryKey(roleId);
    }

    @Override
    public void updateRole(Integer roleId, Role role) {
        role.setRoleId(roleId);
        mapper.updateByPrimaryKey(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        mapper.deleteByPrimaryKey(roleId);
    }
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        return mapper.selectAssignedRoles(adminId);
    }

    @Override
    public List<Role> getUnassignedRole(Integer adminId) {
        return mapper.selectUnassignedRoles(adminId);
    }
}
