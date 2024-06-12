package com.spring.user.client;


import com.spring.user.DemandeCreditDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "DEMANDECREDIT")
public interface DemandeCreditClient {
    @GetMapping("/user/{id}")
    List<DemandeCreditDto> findAllDemandesCreditByUser(@PathVariable("id") Integer id);
}
