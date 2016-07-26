package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Color;

public interface ColorDao extends GenericDao {

    Color findByColor(String color);
}
