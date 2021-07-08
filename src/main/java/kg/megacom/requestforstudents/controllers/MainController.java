package kg.megacom.requestforstudents.controllers;


import kg.megacom.requestforstudents.models.OrderStatus;
import kg.megacom.requestforstudents.models.Orders;
import kg.megacom.requestforstudents.models.Response;
import kg.megacom.requestforstudents.models.dto.MainDto;
import kg.megacom.requestforstudents.models.dto.OrdersDto;
import kg.megacom.requestforstudents.service.OrderService;
import kg.megacom.requestforstudents.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class    MainController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Response save( @RequestBody  MainDto mainDto){

        return  orderService.saveOrder(mainDto);

    }

    @GetMapping("/get")
    public List<OrdersDto> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PutMapping("update")
    public Response update(@RequestParam OrderStatus status, @RequestParam Long id, @RequestParam(required = false) String comment){
        return  orderService.changeStatus(status,id,comment);
    }
}
