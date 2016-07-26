package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.User;
import java.util.Map;


public interface UserService {

    void addNewUser(User user);

    void updateUser(User user);

    User findByLogin(String login);

    User findById(long id);

    Map<User, Long> getTopTenUsers();

}
