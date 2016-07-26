package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.RoleService;
import services.interfaces.UserService;

import java.util.*;

/**
 * Class for control entity user.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Create new User in Database.
     *
     * @param user
     *          value
     */



    @Override
    public void addNewUser(User user) {
        roleService.findByRoleName("ROLE_CLIENT");
        userDao.create(user);
    }

    /**
     * Update User value in Database
     * @param user
     *      value
     */

    public void updateUser(User user) {
       userDao.merge(user);
    }

    /**
     * Find User by login in Database
     * @param login
     *          value
     * @return
     *      user
     */

    @Override
    public User findByLogin(String login){
        return userDao.findByLogin(login);
    }

    /**
     * Find User by id in Database
     * @param id
     *       user id
     * @return
     *     user
     */

    @Override
    public User findById(long id) {
        return (User) userDao.findById(id);
    }

    /**
     * Get users are most shop
     * @return
     *     map user and proceeds
     */

    @Override
    public Map<User, Long> getTopTenUsers() {
        return sortByValue(userDao.getTopTenUsers());
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
