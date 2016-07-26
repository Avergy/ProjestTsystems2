package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.interfaces.BrandService;
import services.interfaces.RoleService;
import services.interfaces.UserService;

import java.util.List;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private BrandService brandService;

    @Autowired
    private UserService userService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showRegistrationForm()
    {
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registration(@RequestParam("firstName") String firstName,
                               @RequestParam("secondName") String secondName,
                               @RequestParam("birthday")@DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date birthday,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("login") String login)
    {
        User user = new User(login, firstName, secondName, birthday, email, password);
        userService.addNewUser(user);
        return "redirect:/login";
    }
}
