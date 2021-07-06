package kg.megacom.requestforstudents.mappers;

import kg.megacom.requestforstudents.models.Subscribers;
import kg.megacom.requestforstudents.models.dto.SubscribersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriberMapper {

        SubscriberMapper INSTANCE = Mappers.getMapper(SubscriberMapper.class);

        SubscribersDto toSubscribersDto(Subscribers subscribers);


        Subscribers toSubscribers (SubscribersDto subscribersDto);
}
