package com.soma.spenzy.service.mapper;

import com.soma.spenzy.model.DTO.NewSubscriptionDTO;
import com.soma.spenzy.model.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewSubscriptionMapper {
    Subscription toSubscription(NewSubscriptionDTO newSubscriptionDTO);
    NewSubscriptionDTO toNewSubscriptionDTO(Subscription subscription);
}
