package com.spring.user.Client;


import com.spring.user.DTO.DemandeCreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "DemandesCredit-service", url = "${application.config.DemandesCredit-url}")
public interface DemandeCreditClient {
    @GetMapping("/user/{id}")
    List<DemandeCreditDTO> findAllDemandesCreditByUser(@PathVariable("id") Integer id);
}
