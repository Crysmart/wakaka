package com.service.demo.controller;

import com.service.demo.service.IDeptService;
import dao.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/17 17:34
 */
@RestController()
public class DeptController {

    IDeptService iDeptService;
    @Autowired
    public DeptController(IDeptService iDeptService) {
        this.iDeptService = iDeptService;
    }

    @RequestMapping(value = "/dept/getList",method = RequestMethod.GET)
    public List<Dept> getList(){
        return iDeptService.getList();
    }

    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    public Dept getList(@PathVariable Integer id){
        return iDeptService.findOne(id);
    }
}
