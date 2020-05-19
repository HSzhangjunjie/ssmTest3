package com.test.service;

import com.test.bean.Auth;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service
 * @ClassName: IAuthService
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/17 17:01
 * @Version: 1.0
 */
public interface IAuthService {
    /**
     * description: 获取auths集合
     * create time: 18:20 2020/5/17
     *
     * @return auths集合
     */
    public List<Auth> getAuths();

    /**
     * description:获取roleId对应的auth的id
     * create time: 19:09 2020/5/17
     * @param roleId auth的id
     * @return 结果集
     */
    List<Integer> getAuthsIdByRoleId(Integer roleId);

    /**
     * description:保存更新结果
     * create time: 0:07 2020/5/18
     * @param map 更新结果
     */
    void saveAssignedAuths(Map<String, List<Integer>> map);
}
