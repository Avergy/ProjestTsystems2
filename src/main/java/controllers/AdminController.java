package controllers;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import entity.Color;
import entity.Order;
import entity.Phone;
import enums.StatusOrder;
import enums.StatusPayment;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import services.interfaces.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PhoneService phoneService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @RequestMapping(value = "/addPhone", method = RequestMethod.GET)
    public String showFormForAddNewPhone(ModelMap modelMap)
    {
        logger.debug("Show form for add new phone");
        modelMap.addAttribute("colorList", colorService.loadAllColors());
        modelMap.addAttribute("aria-expanded-user", true);
        logger.debug("Returning view 'add_phone'");
        return "admin/add_phone";
    }

    @RequestMapping(value = "/editBrandList", method = RequestMethod.GET)
    public String showFormForChangeBrandList(ModelMap modelMap)
    {
        logger.debug("Show form for change brand list");
        modelMap.addAttribute("aria-expanded-admin", true);
        logger.debug("Returning view 'edit_brand_list'");
        return "admin/edit_brand_list";
    }

    @RequestMapping(value = "/editBrandList", method = RequestMethod.POST)
    public String changeBrandList(@RequestParam("new_brand_name") String new_brand_name,
                                  @RequestParam("select_brand") String select_brand,
                                  @RequestParam(value = "edit", required = false) String edit_btn)
    {
        logger.debug("Change brand list");
        if(edit_btn != null)
        {
            if (select_brand.equals("add new brand"))
            {
                Brand brand = new Brand(new_brand_name);
                brandService.addNewBrand(brand);
            } else {
                Brand brand = brandService.findByBrandName(select_brand);
                brand.setBrand(new_brand_name);
                brandService.updateBrand(brand);
            }
        }
        logger.debug("Redirect to show form for changing brand list");
        return "redirect:/admin/editBrandList";
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String showStatistics(ModelMap modelMap)
    {
        logger.debug("Show statistics");
        modelMap.addAttribute("topTenUsers", userService.getTopTenUsers());
        modelMap.addAttribute("topTenPhones", phoneService.getTopTenPhones());
        modelMap.addAttribute("aria-expanded-admin", true);
        logger.debug("Returning view 'statistics'");
        return "admin/statistics";
    }

    @RequestMapping(value = "/showProceedsPerPeriod")
    public String showProceedsPerPeriod(@RequestParam("from_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date first_date,
                                        @RequestParam("to_date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date second_date,
                                        ModelMap modelMap)
    {
        logger.debug("Show statistics with proceeds per period.");
        modelMap.addAttribute("ordersPerPeriod", orderService.getOrdersPerPeriod(first_date, second_date));
        modelMap.addAttribute("topTenUsers", userService.getTopTenUsers());
        modelMap.addAttribute("topTenPhones", phoneService.getTopTenPhones());
        logger.debug("Redirect to show statistics");
        return "admin/statistics";
    }

    @RequestMapping(value = "/addPhone", method = RequestMethod.POST)
    public String addNewPhone(@ModelAttribute("new_phone") Phone new_phone,
                              @RequestParam(value = "phone_name") String phone_name,
                              @RequestParam("phone_brand") String phone_brand,
                              @RequestParam("phone_color") String phone_color,
                              @RequestParam("phone_weight") int phone_weight,
                              @RequestParam("phone_quantity_stock") int phone_quantity_stock,
                              @RequestParam String new_color,
                              @RequestParam("phone_price") int phone_price,
                              @RequestParam(value ="phone_image", required = false) CommonsMultipartFile file)
    {
        logger.debug("Add new phone.");
        if(!new_color.equals(""))
        {
            Color color = new Color(new_color);
            colorService.addNewColor(color);
            phone_color = new_color;
        }

        Phone phone = new Phone(phone_name, phone_price,phone_weight, phone_quantity_stock);

        if (file.getBytes().length != 0)
            phone.setImages(1);
        else
            phone.setImages(0);
        phone = phoneService.addNewPhone(phone, phone_brand, phone_color);
        if (file.getBytes().length != 0) {
            File file1 = new File("C:\\glassfish4\\glassfish\\domains\\domain1\\applications\\" +
                    "ProjectTSystems2-1.0-SNAPSHOT\\resources\\images\\phoneImages\\" + phone.getId() + ".jpg");
            try(FileOutputStream outputStream = new FileOutputStream(file1)) {
                outputStream.write(file.getBytes());
            } catch (IOException e) {
               // phone.setImages(0);
               // phoneService.updatePhone(phone);
            }
            //book.setImage(locationMapFileData.getBytes());
        }
        logger.debug("Redirect to show form for adding phone");
        return "redirect:/admin/addPhone";
    }

    @RequestMapping("/orderList")
    public String showAllOrders(ModelMap modelMap)
    {
        logger.debug("Show all orders in database");
        modelMap.addAttribute("orderList", orderService.getAllOrders());
        modelMap.addAttribute("aria-expanded-admin", true);
        logger.debug("Returning view 'order_list'");
        return "admin/all_order_list";
    }

    @RequestMapping(value = "/editOrder/{id}", method = RequestMethod.GET)
    public String showFormForChangeOrder(@PathVariable long id,
                                         ModelMap modelMap)
    {
        logger.debug("Show form for change order status");
        Order order = orderService.findOrderById(id);
        modelMap.addAttribute(order);
        modelMap.addAttribute("statusOrderValues", StatusOrder.values());
        modelMap.addAttribute("statusPaymentValues", StatusPayment.values());
        modelMap.addAttribute("aria-expanded-admin", true);
        logger.debug("Returning view 'edit_order'");
        return "admin/edit_order";
    }

    @RequestMapping(value = "/editOrder/{id}", method = RequestMethod.POST)
    public String editOrder(@PathVariable long id,
                            @RequestParam String order_status,
                            @RequestParam String payment_status)
    {
        logger.debug("Edit order status.");
        orderService.updateOrder(id, StatusOrder.getEnumValue(order_status), StatusPayment.getEnumValue(payment_status));
        logger.debug("Redirect to show order list");
        return "redirect:/admin/orderList";
    }

    @RequestMapping(value = "/editPhoneDetail/{id}", method = RequestMethod.GET)
    public String showViewEditPhoneDetail(@PathVariable long id, ModelMap modelMap)
    {
        logger.debug("Show form for edit phone detail");
        modelMap.addAttribute("colorList", colorService.loadAllColors());
        modelMap.addAttribute("phone", phoneService.findPhoneById(id));
        logger.debug("Returning view 'edit_phone'");
        return "admin/edit_phone";
    }

    @RequestMapping(value = "/editPhoneDetail/{id}", method = RequestMethod.POST)
    public String editPhoneDetail(@PathVariable long id,
                                  @RequestParam("phone_name") String phone_name,
                                  @RequestParam("phone_brand") String phone_brand,
                                  @RequestParam("phone_color") String phone_color,
                                  @RequestParam("phone_weight") String phone_weight,
                                  @RequestParam("phone_quantity_stock") String phone_quantity_stock,
                                  @RequestParam("phone_price") String phone_price)
    {
        logger.debug("Edit phone detail.");
        Phone phone = new Phone(phone_name, Integer.parseInt(phone_price),
                Integer.parseInt(phone_weight), Integer.parseInt(phone_quantity_stock));
        phone.setId(id);
        phoneService.updatePhone(phone, phone_brand, phone_color);
        logger.debug("Redirect to show form for edit phone detail");
        return "redirect:/admin/editPhoneDetail/" + id;
    }
}
