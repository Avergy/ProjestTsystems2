package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import entity.Color;
import entity.Phone;
import exceptions.NotEnoughException;

import java.util.List;
import java.util.Map;

public interface PhoneDao extends GenericDao {

    List<Phone> findByBrand(Brand brand);

    List<Phone> findByName(String phoneName);

    List<Phone> findByColor(Color color);

    Map<Phone, Integer> getTopTenPhones();
}
