package com.wakaka.sckill_redis.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Crysmart
 * @date 2021/1/29 15:34
 */
@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
    private String password;

}
