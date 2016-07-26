package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.RoleDao;
import entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.RoleService;

/**
 * Class for control entity user role.
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    /**
     *
     * Find Role by name in Database
     *
     * @param role
     *      String value name Role
     * @return
     *      role
     */
    @Override
    public Role findByRoleName(String role) {
        return roleDao.findByRole(role);
    }
}
