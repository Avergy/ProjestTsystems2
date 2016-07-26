package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import entity.Phone;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import services.interfaces.BrandService;
import services.interfaces.PhoneService;

import java.util.List;


@Controller
public class PhonesController {

    private static final Logger logger = Logger.getLogger(PhonesController.class);

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @RequestMapping("/phoneList/{brand}")
    public String viewPhoneList(@PathVariable("brand") String brandName, ModelMap modelMap)
    {
        logger.debug("Show phone list.");
        List<Phone> phoneList;
        if(brandName.equals("all"))
            phoneList = phoneService.loadAllPhones();
        else
            phoneList = phoneService.loadPhonesByBrand(brandName);
        modelMap.addAttribute(phoneList);
        logger.debug("Returning phone list view.");
        return "phone_list";
    }

    @RequestMapping("/phoneDetail/{id}")
    public String viewPhoneDetail(@PathVariable("id") long phoneId, ModelMap modelMap)
    {
        logger.debug("Show phone detail.");
        Phone phone = phoneService.findPhoneById(phoneId);
        modelMap.addAttribute(phone);
        logger.debug("Returning phone detail view.");
        return "phone_detail";
    }
}
