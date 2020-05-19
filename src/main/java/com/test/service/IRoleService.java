package com.test.service;

import com.github.pagehelper.PageInfo;
import com.test.bean.Role;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service
 * @ClassName: IRoleService
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/11 20:00
 * @Version: 1.0
 */
public interface IRoleService {

    /**
     * description:根据关键字查找
     * create time: 20:23 2020/5/11
     *
     * @param keyword 关键字
     * @return PageInfo结果集
     */
    PageInfo<Role> findByKeyword(Integer pageNum, Integer pageSize, String keyword);

    /**
     * description: 增加角色信息
     * create time: 1:19 2020/5/14
     *
     * @param role 角色信息
     */
    void addRole(Role role);

    /**
     * description: 根据ID查找
     * create time: 2:02 2020/5/14
     *
     * @return Role对象
     */
    Role findById(Integer roleId);

    /**
     * description: 更新角色信息
     * create time: 2:50 2020/5/14
     *
     * @param role   角色信息
     * @param roleId 角色Id
     */
    void updateRole(Integer roleId, Role role);

    /**
     * description: 删除角色信息
     * create time: 3:06 2020/5/14
     *
     * @param roleId 角色Id
     */
    void deleteRole(Integer roleId);
    /**
     * description: 获取被声明的角色对象
     * create time: 13:03 2020/5/17
     * @param adminId 用户id
     * @return 获取被声明的角色对象
     */
    List<Role> getAssignedRole(Integer adminId);

    /**
     * description: 获取未被声明的角色对象
     * create time: 13:03 2020/5/17
     * @param adminId 用户id
     * @return 获取未被声明的角色对象
     */
    List<Role> getUnassignedRole(Integer adminId);
}
