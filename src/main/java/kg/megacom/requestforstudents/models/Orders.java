package kg.megacom.requestforstudents.models;

import javax.persistence.*;
import java.util.Date;

public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    private String schoolName;
    private String address;
    private Date addDate;
    private Date endDate;
    private Date naviDate;
    private String comment;

    @ManyToOne
    @JoinColumn( name = "id_subscriber")
    private Subscribers idSubscriber;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
