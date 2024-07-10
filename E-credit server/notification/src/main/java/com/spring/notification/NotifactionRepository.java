package com.spring.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifactionRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long userId);
}
