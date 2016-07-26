package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.UserDao;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class UserDaoImpl extends AbstractGenericDao implements UserDao {

    final static Logger logger = Logger.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoImpl() {
        super();
        setEntityClass(User.class);
    }

    public User findByLogin(String login){
        logger.debug("Find user by login in the DB.");
        User user;
        Query query = entityManager.createQuery("Select user FROM User user WHERE user.login = :login");
        query.setParameter("login", login);
        user = (User) findOne(query);
        logger.debug("Returning found user.");
        return user;
    }

    @Override
    public Map<User, Long> getTopTenUsers() {

        logger.debug("Find top ten users.");
        Map<User, Long> topUsers = new HashMap<>();

        String sql = "select orders.idUser, sum(orders.Cost) FROM orders GROUP BY idUser ORDER BY sum(orders.Cost) DESC LIMIT 10";

        List<Object[]> resultList = entityManager.createNativeQuery(sql).getResultList();

        for (Object[] result : resultList) {
            BigInteger clientId = (BigInteger) result[0];
            long id = clientId.longValue();

            BigDecimal clientSum = (BigDecimal) result[1];
            long summ = clientSum.longValue();

            topUsers.put((User) findById(id), summ);
        }

        logger.debug("Returning top ten users.");
        return topUsers;
    }
}
