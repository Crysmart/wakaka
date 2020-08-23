package com.service.feigndemo.service.impl;

import com.service.feigndemo.service.IDeptService;
import dao.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Crysmart
 * @date 2020/8/18 11:59
 */
@Service
public class DeptServiceImpl implements IDeptService {

    //private final static String ROOT_CONTEXT = "http://localhost:8080";
    private final static String ROOT_CONTEXT = "http://SERVICE-PROVIDER";

    RestTemplate restTemplate;

    @Autowired
    public DeptServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Dept> getList(){
        return restTemplate.getForObject(ROOT_CONTEXT+"/dept/getList",List.class);
    }

    @Override
    public Dept findOne(Integer id) {
        return restTemplate.getForObject(ROOT_CONTEXT + "/dept/findOne/" + id, Dept.class);
    }
}
