package com.base_alumno.dto;

import com.base_alumno.model.PushSubscriptionModel;

public record PushSubscriptionDto(
        String endpoint,

        Long expirationTime,

        Keys keys) {
    public record Keys(
            String p256dh,

            String auth) {
    }

    public PushSubscriptionModel toModel() {

        PushSubscriptionModel model = new PushSubscriptionModel();
        model.setEndpoint(this.endpoint);
        model.setAuth(this.keys.auth());
        model.setP256dh(this.keys.p256dh());

        return model;
    }

}