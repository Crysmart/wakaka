package com.wakaka.framework.jwt.create;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;

/**
 * @author Crysmart
 * @date 2020/7/22 15:59
 */
public class JwtDemo {

    public static void main(String[] args) {
        String qwer = createToken("qwer", "1");
        System.out.println(qwer);
    }

    /**
     * 创建token
     * @param secret
     * @param id
     * @return
     */
    public static String createToken(String secret, String ...id){
        return JWT.create()
                .withAudience(id)
                .withExpiresAt(DateUtil.yesterday())
                .sign(Algorithm.HMAC256(secret));
    }
}
