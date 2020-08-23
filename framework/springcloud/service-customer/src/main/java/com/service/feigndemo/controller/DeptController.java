package com.service.feigndemo.controller;

import com.service.feigndemo.service.IDeptService;
import dao.entity.Dept;
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
@RequestMapping("/dept")
public class DeptController {

    IDeptService iDeptService;

    public DeptController(IDeptService iDeptService){
        this.iDeptService=iDeptService;
    }

    @RequestMapping(value = "/dept/getList",method = RequestMethod.GET)
    public List<Dept> getList(){
        return iDeptService.getList();
    }

    @RequestMapping(value = "/dept/findOne/{id}",method = RequestMethod.GET)
    public Dept Dept(@PathVariable Integer id){
        return iDeptService.findOne(id);
    }
}
