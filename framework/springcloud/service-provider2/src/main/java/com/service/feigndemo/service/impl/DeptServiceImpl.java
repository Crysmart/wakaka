package com.service.feigndemo.service.impl;

import com.service.feigndemo.dao.DeptMapper;
import com.service.feigndemo.service.IDeptService;
import dao.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/17 17:35
 */
@Service
public class DeptServiceImpl implements IDeptService {

    DeptMapper deptMapper;

    @Autowired
    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> getList() {
        return deptMapper.getList();
    }

    @Override
    public Dept findOne(Integer id) {
        return deptMapper.findOne(id);
    }
}
