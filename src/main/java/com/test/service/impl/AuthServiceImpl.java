package com.test.service.impl;

import com.test.bean.Auth;
import com.test.dao.mapper.AuthMapper;
import com.test.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service.impl
 * @ClassName: AuthServiceImpl
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/17 17:01
 * @Version: 1.0
 */
@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthMapper mapper;

    @Override
    public List<Auth> getAuths() {
        return mapper.selectByExample(null);
    }

    @Override
    public List<Integer> getAuthsIdByRoleId(Integer roleId) {
        return mapper.getAuthsIdByRoleId(roleId);
    }

    @Override
    public void saveAssignedAuths(Map<String, List<Integer>> map) {
        // 获取RoleId的值
        Integer roleId = map.get("roleId").get(0);
        // 删除就关系数据
        mapper.deleteOldAuths(roleId);
        // 获取Auths集合
        List<Integer> authsId = map.get("authArray");
        if (authsId != null && authsId.size() > 0) {
            mapper.insertNewAuths(roleId, authsId);
        }
    }
}
