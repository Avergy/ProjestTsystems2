package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.ColorDao;
import entity.Color;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class ColorDaoImpl extends AbstractGenericDao implements ColorDao {

    final static Logger logger = Logger.getLogger(ColorDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    public ColorDaoImpl() {
        super();
        setEntityClass(Color.class);
    }

    public Color findByColor(String color) {
        logger.debug("Find color by name.");
        Query query = entityManager.createQuery("Select c FROM Color c WHERE c.color = :color");
        query.setParameter("color", color);
        Color color1 = (Color) findOne(query);
        logger.debug("Returning found color.");
        return color1;
    }
}
