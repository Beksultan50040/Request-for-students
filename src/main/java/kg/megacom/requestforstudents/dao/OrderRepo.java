package kg.megacom.requestforstudents.dao;

import kg.megacom.requestforstudents.models.OrderStatus;
import kg.megacom.requestforstudents.models.Orders;
import kg.megacom.requestforstudents.models.Subscribers;
import kg.megacom.requestforstudents.models.dto.OrdersDto;
import kg.megacom.requestforstudents.models.dto.SubscribersDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    Orders findAllByIdSubscriber(Subscribers subscribers);


}
