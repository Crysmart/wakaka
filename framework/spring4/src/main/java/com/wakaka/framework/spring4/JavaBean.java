package com.wakaka.framework.spring4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Crysmart
 */
@Component
public class JavaBean {
    /**
     * {@link Value} 使用SpEL表达式进行对象初始化的载入
     */
    @Value("#{T(System).currentTimeMillis().toString()}")
    private String username;
    /**
     * {@link Value} 使用EL表达式进行从properties文件中读取数据注入
     */
    @Value("${password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
