package com.wakaka.sckill_redis.repository;

import com.wakaka.sckill_redis.entity.Product;
import com.wakaka.sckill_redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Crysmart
 * @date 2021/1/29 15:21
 */
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
