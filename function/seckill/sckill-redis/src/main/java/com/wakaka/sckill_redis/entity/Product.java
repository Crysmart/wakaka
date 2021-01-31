package com.wakaka.sckill_redis.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Crysmart
 * @date 2021/1/29 16:19
 */
@Entity(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    private String productName;
    private Integer count;
}
