package com.wakaka.server1.entity;

/**
 * @author Crysmart
 * @date 2020/10/24 15:24
 */
public class DbServer {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DbServer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
