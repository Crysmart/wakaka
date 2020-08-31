package dao.service;

import dao.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Feign调用
 * @author Crysmart
 * @date 2020/8/23 16:39
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public List<Dept> getList() {
                Dept dept = new Dept();
                dept.setDeptManager("莫得这个管理");
                dept.setDeptName("莫得这个名字");
                dept.setMicroName("服务好像挂了");
                List<Dept> list = new ArrayList<>();
                list.add(dept);
                list.add(dept);
                return list;
            }

            @Override
            public Dept getList(Integer id) {
                Dept dept = new Dept();
                dept.setId(id);
                dept.setDeptManager("莫得这个管理");
                dept.setDeptName("莫得这个名字");
                dept.setMicroName("服务好像挂了");
                return dept;
            }
        };
    }
}
