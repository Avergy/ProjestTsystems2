package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Role;

public interface RoleDao extends GenericDao {

    Role findByRole(String role);
}
