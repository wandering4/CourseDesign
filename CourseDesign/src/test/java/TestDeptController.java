import com.pojo.Dept;
import com.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xuzifan
 * @Date: 2024/5/11 - 05 - 11 - 22:58
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@SpringJUnitConfig(locations = "classpath:ApplicationContext.xml")
public class TestDeptController {
    @Autowired
    private DeptService deptService;

    @Test
    public void testAddDept(){
/*        Dept dept=new Dept(null,"分部","天津","afsdf");
        deptService.addDept(dept);*/
    }

    @Test
    public void testDelDept(){
        /*System.out.println(deptService.delDept(96));*/
    }
    @Test
    public void find(){
        System.out.println(deptService.findByDeptno(10));
    }



}
