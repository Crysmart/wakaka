package com.service.demo.dao;

import dao.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/17 17:06
 */
public interface DeptMapper {
    List<Dept> getList();
    Dept findOne(Integer id);
}
