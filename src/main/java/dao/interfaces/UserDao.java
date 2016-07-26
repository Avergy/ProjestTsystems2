package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.User;

import java.util.Map;

public interface UserDao extends GenericDao {

    User findByLogin(String login);

    Map<User, Long> getTopTenUsers();
}
