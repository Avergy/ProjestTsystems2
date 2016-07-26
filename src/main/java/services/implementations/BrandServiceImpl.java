package services.implementations;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import dao.interfaces.BrandDao;
import entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.BrandService;

import java.util.List;
/**
* Class for control entity brand.
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    /**
     * Create new Brand in Database.
     *
     * @param brand
     *          value
     */
    public void addNewBrand(Brand brand)
    {
        brandDao.create(brand);
    }

    /**
     * Find Brand by brandName in Database
     * @param brandName
     *          String value name brand
     * @return
     *      brand
     */

    @Override
    public Brand findByBrandName(String brandName) {
        return brandDao.findByBrand(brandName);
    }

    /**
     * Load all brands in Database
     * @return
     *      brand list
     */

    public List<Brand> loadAllBrands() {
        return brandDao.findAll();
    }

    /**
     * Update Brand value in Database
     * @param brand
     *      value
     */
    @Override
    public void updateBrand(Brand brand)
    {
        brandDao.merge(brand);
    }

}