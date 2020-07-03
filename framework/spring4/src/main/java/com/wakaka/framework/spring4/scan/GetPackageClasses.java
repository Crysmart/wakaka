package com.wakaka.framework.spring4.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 扫描获取包下的类
 * @author Crysmart
 * @date 2020/7/3 17:02
 */
@Component
public class GetPackageClasses implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    private final ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    private final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);

    /**
     * Set the ResourceLoader that this object runs in.
     * <p>This might be a ResourcePatternResolver, which can be checked
     * through {@code instanceof ResourcePatternResolver}. See also the
     * {@code ResourcePatternUtils.getResourcePatternResolver} method.
     * <p>Invoked after population of normal bean properties but before an init callback
     * like InitializingBean's {@code afterPropertiesSet} or a custom init-method.
     * Invoked before ApplicationContextAware's {@code setApplicationContext}.
     *
     * @param resourceLoader the ResourceLoader object to be used by this object
     * @see ResourcePatternResolver
     * @see ResourcePatternUtils#getResourcePatternResolver
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 利用spring提供的扫描包下面的类信息,再通过classFrom反射获得类信息
     *
     * @param scanPackage 扫描路径
     */
    public Set<Class<?>> doScan(String scanPackage) throws IOException {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                .concat(ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(scanPackage))
                        .concat("/**/*.class"));
        Resource[] resources = resolver.getResources(packageSearchPath);
        MetadataReader metadataReader = null;
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                metadataReader = metadataReaderFactory.getMetadataReader(resource);
                try {
                    // 当类型不是抽象类或接口在添加到集合
                    if (metadataReader.getClassMetadata().isConcrete()) {
                        classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }

}
