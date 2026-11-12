package com.base_alumno.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base_alumno.model.PushSubscriptionModel;

public interface PushSubscriptionRepository extends JpaRepository<PushSubscriptionModel, Long> {

}
