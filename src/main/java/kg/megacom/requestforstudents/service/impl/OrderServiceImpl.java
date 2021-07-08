package kg.megacom.requestforstudents.service.impl;

import kg.megacom.requestforstudents.dao.OrderRepo;
import kg.megacom.requestforstudents.mappers.OrderMapper;
import kg.megacom.requestforstudents.mappers.SubscriberMapper;
import kg.megacom.requestforstudents.models.OrderStatus;
import kg.megacom.requestforstudents.models.Orders;
import kg.megacom.requestforstudents.models.Response;
import kg.megacom.requestforstudents.models.Subscribers;
import kg.megacom.requestforstudents.models.dto.MainDto;
import kg.megacom.requestforstudents.models.dto.OrdersDto;
import kg.megacom.requestforstudents.models.dto.SubscribersDto;
import kg.megacom.requestforstudents.service.OrderService;
import kg.megacom.requestforstudents.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SubscriberService subscriberService;

    // Create new order
    public void createOrder(MainDto mainDto){
        SubscribersDto subscribersDto = subscriberService.findSubsByNumber(mainDto.getNumber());
        Orders orders = new Orders();

        orders.setAddress(mainDto.getAddress());
        orders. setSchoolName(mainDto.getSchoolName());
        orders.setAddDate(new Date());
        orders.setEndDate(null);
        orders.setNaviDate(null);
        orders.setComment(null);
        orders.setIdSubscriber(SubscriberMapper.INSTANCE.toSubscribers(subscribersDto));
        orders.setOrderStatus(OrderStatus.NEW);
        orderRepo.save(orders);
    }

    @Override
    public Response saveOrder(MainDto mainDto) {

        Response response = Response.getResponse();

        // Check if the user already exists
        SubscribersDto subscribersDto = subscriberService.findSubsByNumber(mainDto.getNumber());


        // If not create create new user
        if (subscribersDto == null){

            subscribersDto = new SubscribersDto();
            subscribersDto.setNumber(mainDto.getNumber());
            subscribersDto.setName(mainDto.getName());
            subscribersDto.setBirthDate(mainDto.getBirthDate());
            subscriberService.saveSubscriber(subscribersDto);

            createOrder(mainDto);


        }
        // If yes, check whether the user already sent an order before
        else{
            Orders currOrder = orderRepo.findAllByIdSubscriber(SubscriberMapper.INSTANCE.toSubscribers(subscribersDto));

            // if the order in process notify the user
            if(currOrder.getOrderStatus().equals(OrderStatus.IN_PROCESS)){
                response.setMessage("Ваша услуга рассматривается");
                return  response;
            }

            // if the order status is NEW, APPROVED OR DENIED cancel the old order and create new
            else if (currOrder.getOrderStatus().equals(OrderStatus.NEW) || currOrder.getOrderStatus().equals(OrderStatus.APPROVED)
            || currOrder.getOrderStatus().equals(OrderStatus.DENIED)) {

                currOrder.setOrderStatus(OrderStatus.CANCELED);
                currOrder.setEndDate(new Date());

                orderRepo.save(currOrder);

                createOrder(mainDto);

                response.setMessage("Ваша заявка принята");
                return response;

            }
        }

        return null;
    }

    @Override
    public List<OrdersDto> getAllOrders() {
       List<Orders> ordersList = orderRepo.findAll();
       return OrderMapper.INSTANCE.toOrdersDto(ordersList);

    }

    @Override
    public Response changeStatus(OrderStatus status, Long id, String comment) {

        Response response = Response.getResponse();

        // Find order by id
        Orders orders = orderRepo.findById(id).get();

        if (orders == null){
            response.setMessage("Заявка не найдена");
            return response;
        }
        else
            orders.setOrderStatus(status);

            // If the admin denies the order: set end date, nave date and comment
            if(orders.getOrderStatus().equals(OrderStatus.DENIED))
            {
                orders.setComment(comment);
                orders.setEndDate(new Date());
                orders.setNaviDate(new Date());
                orderRepo.save(orders);
            }
            // If the admin set status "IN_PROCESS" save as it is
            else if(orders.getOrderStatus().equals(OrderStatus.IN_PROCESS)) {
                orderRepo.save(orders);
            }

            // If the admin approves the order: set end date and nave date
            else if(orders.getOrderStatus().equals(OrderStatus.APPROVED)) {
                orders.setEndDate(new Date());
                orders.setNaviDate(new Date());
                orderRepo.save(orders);
            }
            response.setMessage("Заявка была обновлена");
            return  response;
    }



}
