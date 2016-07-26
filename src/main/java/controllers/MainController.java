package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.interfaces.BrandService;
import services.interfaces.PhoneService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private PhoneService phoneService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        logger.debug("Show main page.");
        model.addAttribute("top10", phoneService.getTopTenPhones());
        logger.debug("Returning main page.");
        return "index";
    }
}

