package com.test.service.impl;

import com.test.bean.Menu;
import com.test.dao.mapper.MenuMapper;
import com.test.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssmTest3
 * @Package: com.test.service.impl
 * @ClassName: MenuServiceImpl
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/14 19:44
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper mapper;

    @Override
    public List<Menu> getAll() {
        return mapper.selectByExample(null);
    }

    @Override
    public void addLeaf(Menu menu) {
        mapper.insert(menu);
    }

    @Override
    public void editLeaf(Menu menu) {
        mapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void deleteLeaf(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Menu findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
}
