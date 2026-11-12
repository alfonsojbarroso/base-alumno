# Base Alumno

Aplicación Spring Boot para gestionar información de alumnos y enviar notificaciones push a clientes web registrados.

## Descripción general

Este proyecto combina un backend en Java/Spring con soporte para Web Push Notifications. La idea principal es permitir que un navegador PWA (como la aplicación `pwa-alumno-2`) pueda suscribirse a notificaciones y recibir mensajes del servidor aunque la pestaña no esté activa.

## Tecnologías principales

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Web Push (`nl.martijndwars:web-push`)
- Bouncy Castle para soporte criptográfico VAPID

## Arquitectura de notificaciones push

El flujo completo es el siguiente:

1. El navegador solicita permiso para recibir notificaciones.
2. El cliente crea una suscripción push con `PushManager.subscribe()`.
3. La suscripción se envía al backend mediante una petición HTTP.
4. El backend almacena la suscripción en la base de datos.
5. Cuando se desea enviar una notificación, el servidor obtiene todas las suscripciones activas.
6. El servicio backend usa la clave privada VAPID para firmar el mensaje.
7. El navegador recibe el evento `push` y el Service Worker muestra la notificación al usuario.

## Componentes clave

### `PushController`
Archivo: `src/main/java/com/base_alumno/controller/PushController.java`

Este controlador expone los endpoints necesarios para:

- registrar una suscripción del cliente
- disparar un mensaje de prueba a todos los dispositivos suscritos

Endpoints principales:

```http
POST /api/push/subscribe
GET /api/push/send-test
```

### `PushSubscriptionModel`
Archivo: `src/main/java/com/base_alumno/model/PushSubscriptionModel.java`

Representa la suscripción push del navegador. Guarda la información mínima necesaria para enviar mensajes laterales al cliente:

- `endpoint`
- `p256dh`
- `auth`

Estos campos son los que el servidor usa para construir la suscripción de Web Push en Java.

### `PushServiceManager`
Archivo: `src/main/java/com/base_alumno/service/PushServiceManager.java`

Es el servicio encargado de:

- inicializar el cliente VAPID con la clave pública y privada
- recorrer todas las suscripciones guardadas
- enviar mensajes push a cada endpoint registrado

La clase usa la librería `web-push` de la siguiente forma:

```java
pushService = new PushService(publicKey, privateKey, subject);
Notification notification = new Notification(sub, messagePayload);
pushService.send(notification);
```

## Configuración VAPID

Para que el backend pueda enviar notificaciones push, necesita un par de claves VAPID (Voluntary Application Server Identification): pública y privada.

Se recomienda configurarlas como propiedades de aplicación, por ejemplo en `src/main/resources/application.properties`:

```properties
vapid.public.key=TU_PUBLIC_KEY_VAPID
vapid.private.key=TU_PRIVATE_KEY_VAPID
vapid.subject=mailto:admin@ejemplo.com
```

El valor de `subject` normalmente se usa como identificador del remitente; suele ser un correo electrónico o un URL.

### Generar claves VAPID

Desde la terminal, puedes generarlas con Node.js:

```bash
npx web-push generate-vapid-keys
```

Salida típica:

```text
Public Key: BEl62iUYgUivxIkv69yViEuiBIa-Ib9-SkvMeAtA3LFgDzkrxZJjSgSnfckjBJuBkr3qBUYIHBQFLXYp5Nks8Wk
Private Key: 12345abcdef...
```

Importante:

- La clave pública se comparte con el navegador frontend.
- La clave privada se mantiene únicamente en el backend.
- Nunca se debe exponer la clave privada en el cliente.

## Ejemplo de suscripción enviada por el cliente

La app cliente envía una suscripción con este formato aproximado:

```json
{
  "endpoint": "https://fcm.googleapis.com/fcm/send/...",
  "p256dh": "BIPLTls4...",
  "auth": "xZttZQ=="
}
```

El backend recibe este objeto y lo guarda en la tabla `push_subscriptions`.

## Ejemplo de payload push

El backend puede enviar un JSON con contenido para la notificación:

```json
{
  "title": "¡Alerta de LocalShop!",
  "body": "Hay nuevos productos disponibles en el catálogo.",
  "url": "/"
}
```

En el navegador, el Service Worker interpreta este payload y lo muestra con `showNotification()`.

## Endpoint de prueba

El proyecto incluye un endpoint de prueba para validar la integración:

```http
GET /api/push/send-test
```

Ese endpoint dispara un push a todos los dispositivos suscritos y devuelve un mensaje de confirmación.

## Requisitos del navegador

La funcionalidad push requiere:

- HTTPS en producción o `localhost` en desarrollo
- soporte de `Service Worker`
- soporte de `Notification API`
- soporte de `PushManager`

## Consideraciones importantes

- Las suscripciones pueden invalidarse con el tiempo, por ejemplo si el usuario revoca permisos o cambia de navegador.
- El servidor debe limpiar suscripciones no válidas cuando reciba errores como `410 Gone`.
- El backend debe mantener una tabla con las suscripciones para poder enviar mensajes masivos o dirigidos.
- Las notificaciones push no son una sustitución de sockets en tiempo real; son útiles para alertas, recordatorios y mensajes puntuales.

## Ejecución local

```bash
./mvnw spring-boot:run
```

O bien:

```bash
./mvnw clean package
java -jar target/base_alumno-0.0.1-SNAPSHOT.jar
```

## Resumen

La implementación actual del backend permite registrar suscripciones de clientes web y enviar mensajes push a todos los dispositivos conectados. La configuración VAPID es esencial para la autenticación del servidor y la entrega segura de notificaciones en navegadores compatibles.


### Author
||
|:--:|
|Dr. Alfonso José Barroso Barajas|
|Technology Architect|