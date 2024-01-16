package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.SubscriptionDTO;
import com.soma.spenzy.model.Subscription;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionDTO toSubscriptionDTO(Subscription subscription);
    Set<SubscriptionDTO> toSubscriptionDTOs(Set<Subscription> subscriptions);
    Subscription toSubscription(SubscriptionDTO subscriptionDTO);
    Set<Subscription> toSubscriptions(Set<SubscriptionDTO> subscriptionDTOs);
}
