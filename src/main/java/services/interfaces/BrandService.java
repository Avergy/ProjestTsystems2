package services.interfaces;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;

import java.util.List;

public interface BrandService {

    void addNewBrand(Brand brand);

    Brand findByBrandName(String brandName);

    List<Brand> loadAllBrands();

    void updateBrand(Brand brand);
}
