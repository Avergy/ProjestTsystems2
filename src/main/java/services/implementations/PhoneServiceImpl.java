package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.PhoneDao;
import entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.BrandService;
import services.interfaces.ColorService;
import services.interfaces.PhoneService;

import java.util.*;

/**
 * Class for control entity phone.
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {


    @Autowired
    private PhoneDao phoneDao;

    @Autowired
    private ColorService colorService;

    @Autowired
    private BrandService brandService;

    public void setPhoneDao(PhoneDao phoneDao) {
        this.phoneDao = phoneDao;
    }

    /**
     * Create new Phone in Database.
     *
     * @param phone
     *          value
     */



    public Phone addNewPhone(Phone phone, String phone_brand, String phone_color){
        phone.setBrand(brandService.findByBrandName(phone_brand));
        phone.setColor(colorService.findColorByName(phone_color));
        return (Phone) phoneDao.merge(phone);
    }

    /**
     * Update Phone value in Database
     * @param phone
     *      value
     */

    @Override
    public void updatePhone(Phone phone, String phone_brand, String phone_color) {
        phone.setImages(((Phone)phoneDao.findById(phone.getId())).getImages());
        phone.setBrand(brandService.findByBrandName(phone_brand));
        phone.setColor(colorService.findColorByName(phone_color));
        phoneDao.merge(phone);
    }

    /**
     * Load all phones in Database
     * @return
     *      phone list
     */

    public List<Phone> loadAllPhones() {
        return phoneDao.findAll();
    }

    /**
     * Find Phone by id in Database
     * @param id
     *       phone id
     * @return
     *     phone
     */

    @Override
    public Phone findPhoneById(long id) {
        return (Phone) phoneDao.findById(id);
    }

    /**
     * Update phone in Database
     * @param phone
     *      phone
     *
     */

    @Override
    public void updatePhone(Phone phone){
        phoneDao.merge(phone);
    }

    /**
     * Get phones that often buy
     * @return
     *      map phone and the number of sales
     */
    @Override
    public Map<Phone, Integer> getTopTenPhones() {

        return sortByValue(phoneDao.getTopTenPhones());
    }

    /**
     * Find Phones by brand in Database
     * @param brandName
     *          brand value
     * @return
     *     phone list
     */

    @Override
    public List<Phone> loadPhonesByBrand(String brandName) {
        return phoneDao.findByBrand(brandService.findByBrandName(brandName));
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
