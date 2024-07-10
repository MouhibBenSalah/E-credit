package com.spring.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class NotificationService {

    private  NotifactionRepository notifRepository;

    public List<Notification> getAllNotifications(){
        return notifRepository.findAll();
    }

    public Notification createNotif(Notification notif){
        return notifRepository.save(notif);
    }
    public Notification getNotifById(Long Id) {
        return notifRepository.findById(Id).orElse(null);
    }
    public String deleteNotif(Long id) {
        boolean exists = notifRepository.existsById(id);
        if (!exists)
            throw new IllegalStateException("Notification with id " + id + " does not exist");
        notifRepository.deleteById(id);
        return "Notification with id " + id + " was successfully deleted";
    }



    public List<Notification> findAllNotifByUser(Long userId) {
        return notifRepository.findAllByUserId(userId);
    }



}
