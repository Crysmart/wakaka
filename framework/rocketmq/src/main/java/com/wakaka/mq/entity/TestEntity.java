package com.wakaka.mq.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Crysmart
 * @date 2021/2/26 16:49
 */
@Entity
@Table(name = "test1")
public class TestEntity {
    private Long id;
    private String name;
    private String name2;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}
