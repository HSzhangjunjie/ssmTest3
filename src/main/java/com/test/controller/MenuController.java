package com.test.controller;

import com.test.bean.Menu;
import com.test.dao.entity.ResultEntity;
import com.test.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.controller
 * @ClassName: MenuController
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/14 19:45
 * @Version: 1.0
 */
@Controller
public class MenuController {
    @Autowired
    private IMenuService service;

    /**
     * description: 获取整个树
     * create time: 21:11 2020/5/14
     *
     * @return 树的根节点
     */
    @RequestMapping(value = "/admin/menus", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<Menu> getWholeTree() {
        // 获取全部Menu
        List<Menu> menus = service.getAll();
        // 声明一个根节点
        Menu root = null;
        // 构建id->Menu映射关系
        Map<Integer, Menu> menuMap = new HashMap<>();
        for (Menu menu : menus) {
            menuMap.put(menu.getId(), menu);
        }
        // 创建父子关系
        for (Menu menu : menus) {
            Integer pid = menu.getPid();
            if (pid == null) {
                root = menu;
            } else {
                Menu parentMenu = menuMap.get(pid);
                parentMenu.getChildren().add(menu);
            }
        }
        return ResultEntity.successWithData(root);
    }

    /**
     * description: 添加一个树结点
     * create time: 21:11 2020/5/14
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/admin/menus", method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity addTreeLeaf(Menu menu) {
        try {
            service.addLeaf(menu);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed("节点名称已存在");
        }
    }

    /**
     * description: 修改一个树结点
     * create time: 21:11 2020/5/14
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/admin/menus/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResultEntity editTreeLeaf(@PathVariable Integer id, Menu menu) {
        try {
            menu.setId(id);
            service.editLeaf(menu);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.failed("节点名称已存在");
        }
    }

    /**
     * description: 删除一个树结点
     * create time: 21:11 2020/5/14
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/admin/menus/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity editTreeLeaf(@PathVariable Integer id) {
        service.deleteLeaf(id);
        return ResultEntity.successWithoutData();
    }

    /**
     * description: 单体查询
     * create time: 21:11 2020/5/14
     *
     * @return 查询结果
     */
    @RequestMapping(value = "/admin/menus/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<Menu> getLeaf(@PathVariable Integer id) {
        Menu menu = service.findById(id);
        return ResultEntity.successWithData(menu);
    }
}
