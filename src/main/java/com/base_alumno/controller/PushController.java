package com.base_alumno.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.base_alumno.model.PushSubscriptionModel;
import com.base_alumno.respository.PushSubscriptionRepository;
import com.base_alumno.service.PushServiceManager;

@RestController
@RequestMapping("/api/push")
public class PushController {

    private final PushSubscriptionRepository subscriptionRepository;
    private final PushServiceManager pushServiceManager;

    public PushController(PushSubscriptionRepository subscriptionRepository, PushServiceManager pushServiceManager) {
        this.subscriptionRepository = subscriptionRepository;
        this.pushServiceManager = pushServiceManager;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody PushSubscriptionModel subscription) {
        subscriptionRepository.save(subscription);
        return ResponseEntity.ok("Suscripción guardada exitosamente en el servidor.");
    }

    @GetMapping("/send-test")
    public ResponseEntity<String> triggerPush() {
        String payload = "{\"title\": \"¡Alerta de LocalShop!\", \"body\": \"Hay nuevos productos disponibles en el catálogo.\", \"url\": \"/\"}";

        pushServiceManager.sendNotificationToAll(payload);

        return ResponseEntity.ok("Notificaciones push enviadas correctamente a todos los dispositivos.");
    }
}