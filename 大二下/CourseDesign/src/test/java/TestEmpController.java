import com.dto.EmpBasicDTO;
import com.github.pagehelper.PageInfo;
import com.pojo.EmpBasic;
import com.pojo.EmpDetail;
import com.pojo.Salary;
import com.service.DeptService;
import com.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: xuzifan
 * @Date: 2024/5/9 - 05 - 09 - 17:26
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@SpringJUnitConfig(locations = "classpath:ApplicationContext.xml")
public class TestEmpController {

    @Test
    public void testDatabaseConnection() {
        // 数据库连接信息
/*        String url = "jdbc:mysql://127.0.0.1:3306/keshe?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";

        // 尝试连接数据库
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // 如果成功连接，打印成功消息
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            // 如果连接失败，打印错误消息
            System.err.println("Failed to connect to the database: " + e.getMessage());
            // 抛出异常，测试失败
            throw new RuntimeException("Database connection test failed.", e);
        }*/
    }

    @Autowired
    EmpService empService;


    @Test
    public void testDel(){
/*        boolean b = empService.deleteByEmpno(60);
        ArrayList<Object> objects = new ArrayList<>();
        objects.size();
        System.out.println(b);*/
    }

/*    @Test
    public void testEmpFind(){
        Integer empno=16;
        Map<String, Object> byEmpno = empService.findByEmpno(empno);
        System.out.println(byEmpno);
    }*/
    @Test
    public void showAllEmpBasic(){
        System.out.println(empService.getAllEmpBasic());
    }
    @Test
    public void testEmpPage(){
/*        List<EmpBasicDTO> pageList = empService.getPageList(1, 10,"阿","经理").getList();
        System.out.println(pageList);*/
    }

    @Test
    public void testModify(){
/*        EmpBasic empBasic=new EmpBasic(63,"你好",21,"阿飞",94);
        EmpDetail empDetail=new EmpDetail(63,"男","0",16,null,"阿凡达",null);
        Salary salary=new Salary(63,1000.0,10.0);
        empService.modifyEmp(empBasic,empDetail,salary);*/
    }

}
