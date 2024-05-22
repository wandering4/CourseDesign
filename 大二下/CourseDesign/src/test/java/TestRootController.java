import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.service.RootService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @Author: xuzifan
 * @Date: 2024/5/12 - 05 - 12 - 15:42
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
@SpringJUnitConfig(locations = "classpath:ApplicationContext.xml")
public class TestRootController {
    @Autowired
    private RootService rootService;
/*    @Test
    public void TestFind(){
        System.out.println(rootService.findUname("xzf"));
    }*/
    @Test
    public void tes(){
/*        PageInfo<Integer> pageInfo=new PageInfo<>();
        Page<Integer> page=new Page<>();*/
    }
}
