package com.spring.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notif")
public class NotificationController {
    @Autowired
    private NotificationService service;

    @GetMapping
    public List<Notification> getAllNotifs() {
        return service.getAllNotifications();
    }

    @GetMapping(path = "/{id}")
    public Notification getNotifById(@PathVariable("id") int id) {
        return service.getNotifById(id);
    }

    @PostMapping
    public Notification createNotif(@RequestBody Notification notif) {
        return service.createNotif(notif);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteNotif(@PathVariable("id") int id) {
        return service.deleteNotif(id);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Notification>> findAllNotif(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(service.findAllNotifByUser(userId));
    }
}
