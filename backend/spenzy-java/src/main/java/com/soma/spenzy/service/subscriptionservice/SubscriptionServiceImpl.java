package com.soma.spenzy.service.subscriptionservice;

import com.soma.spenzy.model.DTO.NewSubscriptionDTO;
import com.soma.spenzy.model.DTO.SubscriptionDTO;
import com.soma.spenzy.model.SpenzyUser;
import com.soma.spenzy.model.Subscription;
import com.soma.spenzy.model.repository.SubscriptionRepository;
import com.soma.spenzy.model.repository.UserRepository;
import com.soma.spenzy.security.JwtService;
import com.soma.spenzy.service.mapper.NewSubscriptionMapper;
import com.soma.spenzy.service.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final NewSubscriptionMapper newSubscriptionMapper;
    private final JwtService jwtService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository,
                                   SubscriptionMapper subscriptionMapper, NewSubscriptionMapper newSubscriptionMapper,
                                   JwtService jwtService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.subscriptionMapper = subscriptionMapper;
        this.newSubscriptionMapper = newSubscriptionMapper;
        this.jwtService = jwtService;
    }

    @Override
    public Set<SubscriptionDTO> getAllSubscriptions(String token) {
        return subscriptionMapper.toSubscriptionDTOs(subscriptionRepository
                .findAllByUser_Email(jwtService.extractUsername(token.split(" ")[1])));
    }

    // maybe frontend filter
    @Override
    public Set<SubscriptionDTO> getSubscriptionsBetweenDates(String token, LocalDateTime start, LocalDateTime end) {
        return subscriptionMapper.toSubscriptionDTOs(subscriptionRepository
                .findAllByUser_EmailAndDateToPayBetween(
                        jwtService.extractUsername(token.split(" ")[1]), start, end));
    }

    // maybe frontend filter
    @Override
    public Set<SubscriptionDTO> getSubscriptionsBeforeDate(String token, LocalDateTime date) {
        return subscriptionMapper.toSubscriptionDTOs(subscriptionRepository
                .findAllByUser_EmailAndDateToPayBefore(
                        jwtService.extractUsername(token.split(" ")[1]), date));
    }

    @Override
    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public NewSubscriptionDTO addNewSubscription(String token, NewSubscriptionDTO newSubscriptionDTO) {
        SpenzyUser user = userRepository.findByEmail(jwtService.extractUsername(token.split(" ")[1]))
                .orElseThrow(() -> new IllegalArgumentException(
                        "ERROR: User with [%s] id not found.".formatted(newSubscriptionDTO.userId())
                ));

        Subscription newSubscription = newSubscriptionMapper.toSubscription(newSubscriptionDTO);
        newSubscription.setUser(user);

        subscriptionRepository.save(newSubscription);

        return newSubscriptionDTO;
    }
}
