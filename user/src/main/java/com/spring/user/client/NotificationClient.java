package com.spring.user.client;

import com.spring.user.DTO.DemandeCreditDTO;
import com.spring.user.DTO.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "notification-service", url = "${application.config.notification-url}")
public interface NotificationClient {
    @GetMapping("/user/{id}")
    List<NotificationDTO> findAllNotificationByUser(@PathVariable("id") Integer id);
}
