package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.OrderDao;
import entity.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl extends AbstractGenericDao implements OrderDao {

    final static Logger logger = Logger.getLogger(OrderDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public OrderDaoImpl() {
        super();
        setEntityClass(Order.class);
    }

    @Override
    public List<Order> getOrdersPerPeriod(Date periodStart, Date periodEnd) {
        logger.debug("Get orders per period" + periodStart + "-" + periodEnd + ".");
        String sql = "SELECT o FROM Order o WHERE o.date >= :periodStart AND o.date <= :periodEnd";
        Query query = entityManager.createQuery(sql).
                setParameter("periodStart", periodStart).setParameter("periodEnd", periodEnd);
        List<Order> orders = findMany(query);
        logger.debug("Returning found orders.");
        return orders;
    }
}
