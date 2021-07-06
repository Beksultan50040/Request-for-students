package kg.megacom.requestforstudents.mappers;

import kg.megacom.requestforstudents.models.Orders;
import kg.megacom.requestforstudents.models.dto.MainDto;
import kg.megacom.requestforstudents.models.dto.OrdersDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Orders toOrders (OrdersDto ordersDto);
    OrdersDto toOrdersDto(Orders orders);

}
