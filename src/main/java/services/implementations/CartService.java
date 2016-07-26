package services.implementations;
/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.OrderItem;
import entity.Phone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.interfaces.PhoneService;

import java.util.Iterator;
import java.util.List;

/**
 * Class for control cart.
 */

@Service
public class CartService {

    final static Logger logger = Logger.getLogger(CartService.class);

    @Autowired
    private PhoneService phoneService;

    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    /**
     * Remove order item from cart
     * @param phone
     *      which phone remove
     * @param orderItems
     *      cart
     */
    public void removeOrderItem(Phone phone, List<OrderItem> orderItems) {
        logger.info("Remove orderitem from cart...");
        Iterator<OrderItem> iter = orderItems.iterator();
        while (iter.hasNext()) {
            OrderItem item = iter.next();
            if (item.getPhone().equals(phone)) {
                iter.remove();
            }
        }
        logger.info("Orderitem has been removed from cart.");
    }

    /**
     *  Quantity phones in cart
     * @param orderItems
     *      cart
     * @return
     *      quantity phones
     */
    public int quantityPhonesInCart(List<OrderItem> orderItems) {
        logger.debug("Quantity phones in cart");
        int quantity = 0;
        for (OrderItem item: orderItems)
        {
           quantity += item.getQuantity();
        }
        logger.debug("Returning quantity");
        return quantity;
    }

    /**
     * Total cost phones in cart
     * @param orderItems
     *      cart
     * @return
     *      total cost
     */
    public long totalSum(List<OrderItem> orderItems){
        logger.debug("Total cost phones in cart.");
        long totalSum = 0;
        for (OrderItem item: orderItems)
        {
            Phone phone = phoneService.findPhoneById(item.getPhone().getId());
            totalSum += item.getQuantity() * phone.getPrice();
        }
        logger.debug("Returning total cost.");
        return totalSum;
    }

    /**
     * Update phones information in cart
     * @param orderItems
     *      cart
     */
    public void updatePhonesInCart(List<OrderItem> orderItems){
        logger.debug("Update phones information in cart");
        for (OrderItem item: orderItems)
        {
            item.setPhone(phoneService.findPhoneById(item.getPhone().getId()));
        }
    }

    /**
     * Add phone to cart.
     * @param id
     *      phone id
     * @param orderItems
     *      cart
     * @return
     *      success or not
     */
    public boolean addPhoneToCart(long id, List<OrderItem> orderItems)
    {
        logger.debug("Add phone to cart.");
        Phone phone = phoneService.findPhoneById(id);
        boolean isCartContainPhone = false;
        for(OrderItem item: orderItems)
        {
            if(item.getPhone().equals(phone)){
                logger.debug("cart already contains phone");
                if (item.getQuantity() == phone.getQuantityStock())
                    return false;
                item.setQuantity(item.getQuantity() + 1);
                isCartContainPhone = true;
                break;
            }
        }
        if (!isCartContainPhone){
            logger.debug("cart doesn't contain phone");
            orderItems.add(new OrderItem(phone, 1));
        }
        return true;
    }

    /**
     * Change quantity phone in cart
     * @param id
     *      phone id
     * @param orderItems
     *      cart
     * @param newQuantity
     *      new quantity
     * @return
     *      price for the phone
     */
    public int changeQuantityPhoneInCart(long id, List<OrderItem> orderItems, int newQuantity)
    {
        Phone phone = phoneService.findPhoneById(id);
        for(OrderItem item: orderItems)
        {
            if(item.getPhone().equals(phone)){
                item.setQuantity(newQuantity);
                break;
            }
        }
        return phone.getPrice();
    }
}
