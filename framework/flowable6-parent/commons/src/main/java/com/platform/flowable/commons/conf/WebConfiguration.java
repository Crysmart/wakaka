package com.platform.flowable.commons.conf;


import com.platform.flowable.commons.interceptors.AutoIdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wang.hm
 * @date 2020/3/10 15:54
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Autowired
    public WebConfiguration(AutoIdempotentInterceptor autoIdempotentInterceptor) {
        this.autoIdempotentInterceptor = autoIdempotentInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor)
                .addPathPatterns("/workflow/**")
                .excludePathPatterns("/error");
    }

    /*@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }*/
}
