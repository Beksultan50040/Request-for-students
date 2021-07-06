package kg.megacom.requestforstudents.service;

import kg.megacom.requestforstudents.models.OrderStatus;
import kg.megacom.requestforstudents.models.Orders;
import kg.megacom.requestforstudents.models.Response;
import kg.megacom.requestforstudents.models.dto.MainDto;


import java.util.List;


public interface OrderService {

        Response saveOrder(MainDto mainDto);
        List<Orders> getAllOrders();
        Response changeStatus(OrderStatus status, Long id, String comment);
}
