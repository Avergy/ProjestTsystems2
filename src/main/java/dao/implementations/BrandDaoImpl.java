package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.BrandDao;
import entity.Brand;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class BrandDaoImpl extends AbstractGenericDao implements BrandDao {

    final static Logger logger = Logger.getLogger(BrandDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public BrandDaoImpl() {
        super();
        setEntityClass(Brand.class);
    }

    public Brand findByBrand(String brandName) {
        logger.debug("Find brand by name.");
        Query query = entityManager.createQuery("Select b FROM Brand b WHERE b.brand = :brandName");
        query.setParameter("brandName", brandName);
        Brand brand = (Brand) findOne(query);
        logger.debug("Returning found brand.");
        return brand;
    }


}
