package dao.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.RoleDao;
import entity.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractGenericDao implements RoleDao {

    private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super();
        setEntityClass(Role.class);
    }

    public Role findByRole(String role) {
        logger.debug("Find role by name.");
        Query query = entityManager.createQuery("Select r FROM Role r WHERE r.role = :role");
        query.setParameter("role", role);
        Role role1 = (Role) findOne(query);
        logger.debug("Returning role.");
        return role1;
    }
}
