package com.base_alumno.service;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.base_alumno.model.PushSubscriptionModel;
import com.base_alumno.respository.PushSubscriptionRepository;

import jakarta.annotation.PostConstruct;
import java.security.Security;
import java.util.List;

@Service
public class PushServiceManager {

    private final PushSubscriptionRepository subscriptionRepository;
    private PushService pushService;

    @Value("${vapid.public.key}")
    private String publicKey;
    @Value("${vapid.private.key}")
    private String privateKey;;
    @Value("${vapid.subject}")
    private String subject;

    public PushServiceManager(PushSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostConstruct
    public void init() {
        try {
            // Registrar proveedor de seguridad requerido por la librería web-push
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
            // Inicializar el servicio con tus credenciales VAPID
            pushService = new PushService(publicKey, privateKey, subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToAll(String messagePayload) {
        List<PushSubscriptionModel> subscriptions = subscriptionRepository.findAll();

        for (PushSubscriptionModel subEntity : subscriptions) {
            try {
                // Construir el objeto de suscripción que exige la librería de Java
                nl.martijndwars.webpush.Subscription sub = new nl.martijndwars.webpush.Subscription(
                        subEntity.getEndpoint(),
                        new nl.martijndwars.webpush.Subscription.Keys(
                                subEntity.getP256dh(),
                                subEntity.getAuth()));

                Notification notification = new Notification(sub, messagePayload);
                pushService.send(notification);

            } catch (Exception e) {
                System.err.println("Error al enviar push a la suscripción: " + subEntity.getEndpoint());
                e.printStackTrace();
                // Opcional: Si el error indica que la suscripción ya no es válida (ej. 410
                // Gone), bórrala de la BD.
            }
        }
    }
}