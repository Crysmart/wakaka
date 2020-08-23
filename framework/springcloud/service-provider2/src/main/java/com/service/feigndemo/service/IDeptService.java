package com.service.feigndemo.service;

import dao.entity.Dept;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/17 17:34
 */
public interface IDeptService {
    List<Dept> getList();

    Dept findOne(Integer id);
}
