package com.service.demo.controller;

import com.service.demo.service.IDeptService;
import dao.entity.Dept;
import dao.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/18 14:31
 */
@RestController
@RequestMapping("/customer")
public class DeptCustomerController {

    DeptClientService deptClientService;

    @Autowired
    public DeptCustomerController(DeptClientService deptClientService){
        this.deptClientService=deptClientService;
    }

    @RequestMapping(value = "/dept/getList",method = RequestMethod.GET)
    public List<Dept> getList(){
        return deptClientService.getList();
    }
}
