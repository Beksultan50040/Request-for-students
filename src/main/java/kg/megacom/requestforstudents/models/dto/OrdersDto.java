package kg.megacom.requestforstudents.models.dto;

import kg.megacom.requestforstudents.models.OrderStatus;
import kg.megacom.requestforstudents.models.Subscribers;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class OrdersDto {


    private Long id;

    private String schoolName;
    private String address;
    private Date addDate;
    private Date endDate;
    private Date naviDate;
    private String comment;

    private SubscribersDto idSubscriber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
