package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Phone;
import exceptions.NotEnoughException;

import java.util.List;
import java.util.Map;


public interface PhoneService {

    Phone addNewPhone(Phone phone, String phone_brand, String phone_color);

    List<Phone> loadAllPhones();

    Phone findPhoneById(long id);

    void updatePhone(Phone phone);

    Map<Phone, Integer> getTopTenPhones();

    void updatePhone(Phone phone, String phone_brand, String phone_color);

    List<Phone> loadPhonesByBrand(String brandName);
}
