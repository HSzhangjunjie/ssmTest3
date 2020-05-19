package com.test.service;

import com.test.bean.Menu;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service
 * @ClassName: IMenuService
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/14 19:43
 * @Version: 1.0
 */
public interface IMenuService {
    /**
     * description: 获取菜单列表
     * create time: 20:48 2020/5/14
     * @return 菜单列表
     */
    List<Menu> getAll();

    /**
     * description: 添加节点
     * create time: 21:15 2020/5/15
     * @param menu 节点对象
     */
    void addLeaf(Menu menu);

    /**
     * description: 更改节点信息
     * create time: 21:21 2020/5/15
     * @param menu 节点对象
     */
    void editLeaf(Menu menu);

    /**
     * description: 删除节点信息
     * create time: 21:29 2020/5/15
     * @param id 节点id
     */
    void deleteLeaf(Integer id);

    /**
     * description: 按照Id查询
     * create time: 21:50 2020/5/15
     * @param id 节点id
     * @return 查询结果
     */
    Menu findById(Integer id);
}
