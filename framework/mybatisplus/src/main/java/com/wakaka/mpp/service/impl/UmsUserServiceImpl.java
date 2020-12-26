package com.wakaka.mpp.service.impl;

import com.wakaka.mpp.mapper.UmsUserMapper;
import com.wakaka.mpp.service.IUmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author Crysmart
 * @date 2020/12/25 14:46
 */
@Service
public class UmsUserServiceImpl implements IUmsUserService {
    @Autowired
    UmsUserMapper umsUserMapper;
    
    @Override
    public void get() {
        List<UmsUserMapper> umsUserMappers = umsUserMapper.selectBatchIds(Arrays.asList(1, 2));
        for (UmsUserMapper userMapper : umsUserMappers) {
            System.out.println(umsUserMappers);
        }
    }
}
