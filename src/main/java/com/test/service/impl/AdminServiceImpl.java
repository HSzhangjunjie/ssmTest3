package com.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.bean.Admin;
import com.test.bean.AdminExample;
import com.test.dao.mapper.AdminMapper;
import com.test.exception.LoginFailedException;
import com.test.service.IAdminService;
import com.test.utils.CrowdConstant;
import com.test.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service.impl
 * @ClassName: AdminServiceImpl
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/6 16:11
 * @Version: 1.0
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper mapper;


    @Override
    public void saveAdmin(Admin admin) {
        mapper.insert(admin);
    }

    @Override
    public List<Admin> findAll() {
        return mapper.selectByExample(null);
    }

    @Override
    public Admin findAdminAccount(String loginAccount, String userPassword) {

        if ("".equals(loginAccount) || loginAccount == null || userPassword == null || "".equals(userPassword)) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_NULL_INVALIDATE);
        }

        // 按照账户名称查找Admin对象
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAccountEqualTo(loginAccount);
        List<Admin> admins = mapper.selectByExample(example);
        // 判断账户是否存在
        if (admins.isEmpty()) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_NAME_FAILED);
        } else if (admins.size() > 1) {
            throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_ACCOUNT_NOT_UNIQUE);
        } else {
            // 对输入的密码进行MD5加密
            String password = CrowdUtils.toMd5(userPassword);
            // 判断密码是否正确
            for (Admin admin : admins) {
                if (password.equals(admin.getUserPassword())) {
                    return admin;
                }
            }
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FILED);
        }
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 调用分PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询
        List<Admin> admins = mapper.selectAdminByKeyword(keyword);
        // 封装到pageInfo
        return new PageInfo<>(admins);
    }

    @Override
    public void remove(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void save(Admin admin) {
        mapper.insertSelective(admin);
    }

    @Override
    public Admin findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Admin admin) {
        mapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public void doAssignRoles(Integer adminId, List<Integer> assignedRoles) {
        // 删除旧的关联关系
        mapper.deleteAssignRoles(adminId);
        if (assignedRoles != null &&assignedRoles.size() > 0){
            // 绑定新的关联关系
            mapper.insertAssignRoles(adminId, assignedRoles);
        }
    }
}
