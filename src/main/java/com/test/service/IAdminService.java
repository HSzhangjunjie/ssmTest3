package com.test.service;

import com.github.pagehelper.PageInfo;
import com.test.bean.Admin;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service.impl
 * @ClassName: IAdiminService
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/6 16:09
 * @Version: 1.0
 */
public interface IAdminService {
    /**
     * description: 储存用户信息
     * create time: 16:11 2020/5/6
     *
     * @param admin 用户信息
     */
    void saveAdmin(Admin admin);

    /**
     * description: 查询所有数据
     * create time: 16:11 2020/5/6
     *
     * @return 结果集
     */
    List<Admin> findAll();

    /**
     * description: 查找账户
     * create time: 22:28 2020/5/8
     *
     * @param loginAccount 账户名称
     * @param userPassword 账户密码
     */
    Admin findAdminAccount(String loginAccount, String userPassword);

    /**
     * description: 实现分页和查找功能
     * create time: 18:28 2020/5/9
     * @param keyword 检索词汇
     * @param pageNum 页码
     * @param pageSize 显示记录数
     * @return 结果集
     */
    PageInfo<Admin> getPageInfo(String keyword,Integer pageNum,Integer pageSize);

    /**
     * description:删除用户信息
     * create time: 23:06 2020/5/9
     * @param id 账户id
     */
    void remove(Integer id);

    /**
     * description: 增加账户信息
     * create time: 23:56 2020/5/9
     */
    void save(Admin admin);

    /**
     * description: 按照ID查找用户信息
     * create time: 0:55 2020/5/10
     */
    Admin findById(Integer id);

    /**
     * description: 更新用户信息
     * create time: 1:18 2020/5/10
     */
    void update(Admin admin);
    /**
     * description:处理角色的关联关系
     * create time: 15:22 2020/5/17
     * @param adminId 角色id
     * @param assignedRoles 选择信息
     */
    void doAssignRoles(Integer adminId, List<Integer> assignedRoles);
}
