package com.service.demo.service;

import dao.entity.Dept;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/18 11:59
 */
public interface IDeptService {
    List<Dept> getList();
    Dept findOne(Integer id);
}
