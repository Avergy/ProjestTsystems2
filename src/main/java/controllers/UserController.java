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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import services.interfaces.BrandService;
import services.interfaces.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @ModelAttribute("user")
    public User getUser() {
        logger.debug("Get attribute user");

        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.debug("Returning user.");
        return userService.findByLogin(userDetails.getUsername());
    }


    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showAccount()
    {
        logger.debug("Show user account controller.");
        logger.debug("Returning account view.");
        return "user/account_detail";
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public String editAccount(@RequestParam("firstName") String firstName,
                              @RequestParam("secondName") String secondName,
                              @RequestParam("birthday")@DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date birthday,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam("new_password") String new_password)
    {
        logger.debug("Edit user account.");
        User user = getUser();
        if (user.getPassword().equals(password))
        {
            if (!firstName.equals(""))
                user.setFirstName(firstName);
            if (!secondName.equals(""))
                user.setSecondName(secondName);
            user.setBirthday(birthday);
            if (!email.equals(""))
                user.setEmail(email);
            if (!new_password.equals(""))
                user.setPassword(password);
            userService.updateUser(user);
        }
        logger.debug("Redirect to show user account.");
        return "redirect:/user/account";
    }

    @RequestMapping("/myOrderList")
    public String showUserOrderList(ModelMap modelMap)
    {
        logger.debug("Show user order list.");
        modelMap.addAttribute("orderList", getUser().getOrders());
        logger.debug("Returning order list view");
        return "user/order_list";
    }


}
