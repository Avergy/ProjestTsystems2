package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Role;

public interface RoleService {

    Role findByRoleName(String role);
}
