package dao.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;

public interface BrandDao extends GenericDao {

    Brand findByBrand(String brandName);
}
