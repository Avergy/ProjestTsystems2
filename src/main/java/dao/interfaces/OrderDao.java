package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Order;
import entity.User;

import java.util.Date;
import java.util.List;

public interface OrderDao extends GenericDao {

    List<Order> getOrdersPerPeriod(Date periodStart, Date periodEnd);
}
