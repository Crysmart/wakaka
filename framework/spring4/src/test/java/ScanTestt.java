import com.wakaka.framework.spring4.aop.AopInterface;
import com.wakaka.framework.spring4.scan.GetPackageClasses;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Set;

/**
 * @author Crysmart
 * @date 2020/7/3 17:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-value.xml")
public class ScanTestt {

    @Resource
    private GetPackageClasses getPackageClasses;

    /**
     * 测试AspectJ的Aop
     */
    @Test
    public void function() throws IOException {
        Set<Class<?>> classes = getPackageClasses.doScan("com.wakaka.framework.spring4.aop");
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
    }
}
