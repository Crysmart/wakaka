package dao.service;

import dao.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Feign调用
 * @author Crysmart
 * @date 2020/8/23 16:39
 */
@FeignClient(name = "SERVICE-PROVIDER")
@RequestMapping("/dept")
public interface DeptClientService {

    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    List<Dept> getList();

    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    Dept getList(@PathVariable Integer id);
}
