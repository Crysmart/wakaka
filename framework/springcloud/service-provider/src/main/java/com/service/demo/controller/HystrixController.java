package com.service.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.service.demo.service.IDeptService;
import dao.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    IDeptService iDeptService;
    @Autowired
    public HystrixController(IDeptService iDeptService) {
        this.iDeptService = iDeptService;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public List<Dept> getList(){
        return iDeptService.getList();
    }

    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "setErrorDept")
    public Dept getList(@PathVariable Integer id){
        Dept one = iDeptService.findOne(id);
        if (one == null){
            throw new RuntimeException("这个："+id+"号莫得");
        }
        return one;
    }

    public Dept setErrorDept(Integer id){
        Dept dept = new Dept();
        dept.setId(id);
        dept.setDeptManager("莫得这个管理");
        dept.setDeptName("莫得这个名字");
        dept.setMicroName("服务好像挂了");
        return dept;
    }

}
