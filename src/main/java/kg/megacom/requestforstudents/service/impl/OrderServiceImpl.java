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

        SubscribersDto subscribersDto = subscriberService.findSubsByNumber(mainDto.getNumber());


        if (subscribersDto == null){

            subscribersDto = new SubscribersDto();
            subscribersDto.setNumber(mainDto.getNumber());
            subscribersDto.setName(mainDto.getName());
            subscribersDto.setBirthDate(mainDto.getBirthDate());
            subscriberService.saveSubscriber(subscribersDto);

            createOrder(mainDto);


        }
        else{
            Orders currOrder = orderRepo.findAllByIdSubscriber(SubscriberMapper.INSTANCE.toSubscribers(subscribersDto));

            if(currOrder.getOrderStatus().equals(OrderStatus.IN_PROCESS)){
                response.setMessage("Ваша услуга рассматривается");
                return  response;
            }
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
    public List<Orders> getAllOrders() {
       List<Orders> ordersList = orderRepo.findAll();
       return ordersList;

    }

    @Override
    public Response changeStatus(OrderStatus status, Long id, String comment) {

        Response response = Response.getResponse();
        Orders orders = orderRepo.findById(id).get();

        if (orders == null){
            response.setMessage("Заявка не найдена");
            return response;
        }
        else
            orders.setOrderStatus(status);

            if(orders.getOrderStatus().equals(OrderStatus.DENIED))
            {
                orders.setComment(comment);
                orders.setEndDate(new Date());
                orders.setNaviDate(new Date());
                orderRepo.save(orders);
            }

            orders.setNaviDate(new Date());
            orderRepo.save(orders);
            response.setMessage("Заявка была обновлена");
            return  response;
    }



}
