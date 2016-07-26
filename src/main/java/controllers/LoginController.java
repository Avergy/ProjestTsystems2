package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.interfaces.BrandService;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private BrandService brandService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error)
    {
        logger.debug("Show login page.");
        if (error != null)
            return "error/errorLogin";
        return "login";
    }
}
