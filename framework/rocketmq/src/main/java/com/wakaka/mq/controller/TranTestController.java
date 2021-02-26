package com.wakaka.mq.controller;

import com.wakaka.mq.entity.TestEntity;
import com.wakaka.mq.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Crysmart
 * @date 2021/2/26 16:52
 */
@Controller
public class TranTestController {

    @RequestMapping("trantest")
    public String trantest(){
        TestEntity testEntity = new TestEntity();
        testEntity.setId(111L);
        testEntity.setName("asd");
        testEntity.setName2("zxc");
        //testMapper.insert(testEntity);
        return "66";
    }
}
