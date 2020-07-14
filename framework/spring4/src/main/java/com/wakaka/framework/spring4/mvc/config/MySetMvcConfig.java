package com.wakaka.framework.spring4.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletRegistration.Dynamic;
/**
 * 所有继承这个类的对象，都会被自动配置到DispatcherServlet和Spring应用上下文中
 * @author Crysmart
 * @date 2020/7/8 17:22
 */
public class MySetMvcConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 用来创建ContextLoaderListener的应用上下文
     * 所有类都必须携带@Configuration
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{};
    }

    /**
     * web的配置信息，所有被扫描的文件都会被DispatcherServlet启动时进行加载
     * 所有类都必须携带@Configuration
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    /**
     * 会将一个或多个路径映射到DispatcherServlet中去,本例中映射的是“/”，会处理所有应用请求
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    /**
     * 当DispatcherServlet被加载的时候就会调用这个方法
     * @param registration
     */
    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
    }
}
