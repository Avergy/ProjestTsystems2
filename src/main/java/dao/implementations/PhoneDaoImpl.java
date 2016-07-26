package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.PhoneDao;
import entity.Brand;
import entity.Color;
import entity.Phone;
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
public class PhoneDaoImpl extends AbstractGenericDao implements PhoneDao {

    final static Logger logger = Logger.getLogger(PhoneDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public PhoneDaoImpl() {
        super();
        setEntityClass(Phone.class);
    }

    @Override
    public List<Phone> findByBrand(Brand brand) {
        logger.debug("Find phones in the DB by brand.");
        Query query = entityManager.createQuery("Select p FROM Phone p WHERE p.brand = :brand");
        query.setParameter("brand", brand);
        List<Phone> phones = findMany(query);
        logger.debug("Returning found phones.");
        return phones;
    }

    @Override
    public List<Phone> findByName(String phoneName) {
        logger.debug("Find phones in the DB by name.");
        Query query = entityManager.createQuery("Select p FROM Phone p WHERE p.name = :phoneName");
        query.setParameter("phoneName", phoneName);
        List<Phone> phones = findMany(query);
        logger.debug("Returning found phones.");
        return phones;
    }

    @Override
    public List<Phone> findByColor(Color color) {
        logger.debug("Find phones in the DB by color.");
        Query query = entityManager.createQuery("Select p FROM Phone p WHERE p.color = :color");
        query.setParameter("color", color);
        List<Phone> phones = findMany(query);
        logger.debug("Returning found phones.");
        return phones;
    }

    @Override
    public Map<Phone, Integer> getTopTenPhones() {
        logger.debug("Finding top ten phones.");
        Map<Phone, Integer> topPhones = new HashMap<>();

        String sql = "select orderitems.idPhone, sum(orderitems.Quantity) FROM orderitems GROUP BY idPhone ORDER BY sum(orderitems.Quantity) DESC LIMIT 10";
        List<Object[]> resultList = entityManager.createNativeQuery(sql).getResultList();

        for (Object[] result : resultList) {
            long id = ((BigInteger) result[0]).longValue();
            int totalSold = ((BigDecimal) result[1]).intValue();

            topPhones.put((Phone) findById(id), totalSold);
        }

        logger.debug("Returning top ten phones.");

        return topPhones;
    }

}