import com.test.bean.Admin;
import com.test.bean.Role;
import com.test.dao.mapper.AdminMapper;
import com.test.dao.mapper.RoleMapper;
import com.test.service.IAdminService;
import com.test.utils.CrowdUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @ProjectName: ssmTest3
 * @Package: PACKAGE_NAME
 * @ClassName: CrowdTest
 * @Author: ZhangJunjie
 * @Description:
 * @Date: 2020/5/5 2:50
 * @Version: 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:E:/ideaproject/gradletest/ssmTest3/src/main/resources/config/applicationConfig.xml")
public class CrowdTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IAdminService service;

    @Test
    public void test1() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() throws SQLException {
        Admin admin = new Admin();
        admin.setUserName("王五");
        admin.setEmail("wangwu@qq.com");
        admin.setUserPassword("123456");
        admin.setLoginAccount("WangWu");
        service.saveAdmin(admin);
    }

    @Test
    public void test3() {
        System.out.println(CrowdUtils.toMd5("123456"));
    }

    @Test
    public void test4() {
        for (int i = 0; i < 10000; i++) {
            String name = UUID.randomUUID().toString().substring(0, 6) + i;

            adminMapper.insertSelective(new Admin(null, name, CrowdUtils.toMd5("123456"), name, name + "@qq.com", null));
        }
    }

    @Test
    public void test5() {
        for (int i = 0; i < 1000; i++) {
            String name = UUID.randomUUID().toString().substring(0, 6) + i;

            roleMapper.insertSelective(new Role(null, name));
        }
    }
}
