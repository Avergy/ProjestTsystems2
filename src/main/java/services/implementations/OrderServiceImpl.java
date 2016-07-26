package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.OrderDao;
import entity.Order;
import entity.OrderItem;
import entity.Phone;
import enums.StatusOrder;
import enums.StatusPayment;
import exceptions.NotEnoughException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.OrderService;
import services.interfaces.PhoneService;
import services.interfaces.UserService;

import java.util.Date;
import java.util.List;

/**
 * Class for control entity order
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    final static Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Override
    public void addNewOrder(Order order, String userLogin) throws NotEnoughException {
        for (OrderItem orderItem: order.getOrderItems())
        {
            long id = orderItem.getPhone().getId();
            int quantity = orderItem.getQuantity();
            Phone phone = phoneService.findPhoneById(id);
            int quantityStock = phone.getQuantityStock();
            if (quantityStock >= quantity) {
                phone.setQuantityStock(quantityStock - quantity);
                phoneService.updatePhone(phone);
                logger.debug("Phone quantity has been changed.");
            } else {
                logger.warn("Phone quantity has not been changed.");
                throw new NotEnoughException("In stock only " + quantityStock + " " + phone.getName() + " " + phone.getBrand() + "!");
            }
            orderItem.setOrder(order);
        }
        order.setUser(userService.findByLogin(userLogin));
        cartService.updatePhonesInCart(order.getOrderItems());
        order.setCost(cartService.totalSum(order.getOrderItems()));

        orderDao.merge(order);
    }

    @Override
    public void updateOrder(long id, StatusOrder statusOrder, StatusPayment statusPayment) {
        Order order = (Order) orderDao.findById(id);
        order.setStatusOrder(statusOrder);
        order.setStatusPayment(statusPayment);
        orderDao.merge(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    @Override
    public Order findOrderById(long id) {
        return (Order) orderDao.findById(id);
    }

    /**
     * Get orders per period
     * @param fromDate
     *        from date
     * @param toDate
     *          to date
     * @return
     *      order list
     */

    @Override
    public List<Order> getOrdersPerPeriod(Date fromDate, Date toDate) {
        return orderDao.getOrdersPerPeriod(fromDate, toDate);
    }
}
