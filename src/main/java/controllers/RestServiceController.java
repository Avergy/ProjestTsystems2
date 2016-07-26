package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import Util.Order;
import Util.OrderItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import services.interfaces.OrderService;

import java.util.LinkedList;
import java.util.List;

@RestController
public class RestServiceController {

    private static final Logger logger = Logger.getLogger(RestServiceController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/rest/report", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Order> getOrdersPerPeriod
            (@RequestParam("from_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date first_date,
             @RequestParam("to_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date second_date) {
        logger.debug("Rest: get orders per period.");
        List<entity.Order> list = orderService.getOrdersPerPeriod(first_date, second_date);
        List<Order> orders = new LinkedList<>();
        for(entity.Order order: list) {
            List<OrderItem> newOrderItems = new LinkedList<>();
            for (entity.OrderItem orderItem : order.getOrderItems()) {
                OrderItem item = new OrderItem(order.getId(), orderItem.getQuantity(), orderItem.getPhone().getId(),
                        orderItem.getPhone().getName(), orderItem.getPhone().getPrice(),
                        orderItem.getPhone().getBrand().getBrand(), orderItem.getPhone().getColor().getColor(),
                        orderItem.getPhone().getQuantityStock());
                newOrderItems.add(item);
            }
            Order newOrder = new Order(order.getId(), order.getDate(), order.getUser().getLogin(),
                    order.getUser().getFirstName(), order.getUser().getSecondName(), order.getCost(), newOrderItems);
            orders.add(newOrder);
        }

        logger.debug("Returning order list.");
        return orders;
    }


   /* @RequestMapping(value = "/rest/report/test", method = RequestMethod.GET, produces = "application/pdf")
    public @ResponseBody ModelAndView getOrders(//@RequestParam("from_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date first_date,
                           //@RequestParam("to_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date second_date
                                                ) {
        logger.debug("Rest-test: get orders per period.");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pdfView");
        //modelAndView.addObject("ordersPerPeriod", userService.getOrdersPerPeriod(first_date, second_date));
        logger.debug("Returning order list. test");
        return modelAndView;
    }*/
}
