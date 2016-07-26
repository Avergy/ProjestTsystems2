package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.GenericDao;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    private Class entityClass;
    final static Logger logger = Logger.getLogger(AbstractGenericDao.class);

    @PersistenceContext
    protected EntityManager entityManager;
    public AbstractGenericDao(){
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        logger.debug("Create entity (" + entityClass.getName() +").");
        entityManager.persist(entity);
        logger.debug("Entity has been create.");
    }

    public T merge(T entity) {
        logger.debug("Merging entity (" + entityClass.getName() +").");
        return entityManager.merge(entity);
    }

    public void remove(T entity) {
        logger.debug("Deleting entity (" + entityClass.getName() +").");
        entityManager.remove(entityManager.contains(entity) ? entity :entityManager.merge(entity));
        logger.debug("Entity has been deleted.");
    }


    public List<T> findAll() {
        logger.debug("Searching all of entities (" + entityClass.getName() +").");
        Query query = entityManager.createQuery("from " + entityClass.getName());
        List list = query.getResultList();
        logger.debug("Returning list of entities.");
        return list;
    }

    public T findById(long id) {
        logger.debug("Searching one of entities (" + entityClass.getName() +") by id.");
        T newT = (T) entityManager.find(entityClass, id);
        logger.debug("Returning found entity.");
        return newT;
    }

    public T findOne(Query query) {
        logger.debug("Searching one of entities (" + entityClass.getName() +").");
        T t;
        t = (T) query.getSingleResult();
        logger.debug("Returning found entity.");
        return t;
    }

    @Override
    public List<T> findMany(Query query) {
        logger.info("Searching some entities (" + entityClass.getName() +").");
        List<T> list;
        list = (List<T>) query.getResultList();
        logger.info("Returning List of entities.");
        return list;
    }
}
