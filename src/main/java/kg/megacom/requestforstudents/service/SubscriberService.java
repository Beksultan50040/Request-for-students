package kg.megacom.requestforstudents.service;

import kg.megacom.requestforstudents.models.Response;
import kg.megacom.requestforstudents.models.dto.MainDto;
import kg.megacom.requestforstudents.models.dto.SubscribersDto;

public interface SubscriberService {

    SubscribersDto findSubsByNumber(String number);
    SubscribersDto saveSubscriber(SubscribersDto subscribersDto);
}
