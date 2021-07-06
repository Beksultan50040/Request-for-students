package kg.megacom.requestforstudents.service.impl;

import kg.megacom.requestforstudents.dao.SubscriberRepo;
import kg.megacom.requestforstudents.mappers.SubscriberMapper;
import kg.megacom.requestforstudents.models.Response;
import kg.megacom.requestforstudents.models.Subscribers;
import kg.megacom.requestforstudents.models.dto.MainDto;
import kg.megacom.requestforstudents.models.dto.SubscribersDto;
import kg.megacom.requestforstudents.service.OrderService;
import kg.megacom.requestforstudents.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;


    @Override
    public SubscribersDto findSubsByNumber(String number) {
        Subscribers subscribers = subscriberRepo.findByNumber(number);
        return SubscriberMapper.INSTANCE.toSubscribersDto(subscribers);
    }

    @Override
    public SubscribersDto saveSubscriber(SubscribersDto subscribersDto) {
        Subscribers subscribers = subscriberRepo.save(SubscriberMapper.INSTANCE.toSubscribers(subscribersDto));
        return SubscriberMapper.INSTANCE.toSubscribersDto(subscribers);

        }


    }

