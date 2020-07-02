import com.wakaka.framework.spring4.JavaBean;
import com.wakaka.framework.spring4.JavaBeanSon;
import com.wakaka.framework.spring4.aop.AopInterface;
import com.wakaka.framework.spring4.aop.MainDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author Crysmart
 * @date 2020/7/1 15:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-value.xml")
public class AopTestt {

    @Resource
    private MainDemo mainDemo;
    @Resource
    private JavaBean javaBean;
    @Resource
    private JavaBeanSon javaBeanSon;

    /**
     * 测试AspectJ的Aop
     */
    @Test
    public void fucntion(){
        //被Aop实现的类可以强转成代理对象
        AopInterface inter = (AopInterface) javaBean;
        inter.aopMethod();
    }
}
