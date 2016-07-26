package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Order;
import enums.StatusOrder;
import enums.StatusPayment;
import exceptions.NotEnoughException;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Order findOrderById(long id);

    void addNewOrder(Order order, String userLogin) throws NotEnoughException;

     List<Order> getAllOrders();

    List<Order> getOrdersPerPeriod(Date fromDate, Date toDate);

    void updateOrder(long id, StatusOrder statusOrder, StatusPayment statusPayment);
}
