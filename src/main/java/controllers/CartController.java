package controllers;
/**
 * @author Alina Golubcova
 * @version 1.0
 */

import entity.Brand;
import entity.Order;
import entity.OrderItem;
import enums.Delivery;
import enums.Payment;
import enums.StatusOrder;
import enums.StatusPayment;
import exceptions.NotEnoughException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import services.implementations.CartService;
import services.interfaces.BrandService;
import services.interfaces.OrderService;
import services.interfaces.PhoneService;
import services.interfaces.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/cart")
@SessionAttributes(value ="cart")
public class CartController {

    private static final Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private OrderService orderService;

    @ModelAttribute("brandList")
    public List<Brand> getBrandList()
    {
        logger.debug("Get brand list.");
        return brandService.loadAllBrands();
    }

    @ModelAttribute("cart")
    public ArrayList<OrderItem> createCart()
    {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showCart(ModelMap modelMap, @ModelAttribute("cart") ArrayList<OrderItem> cart,
                           @RequestParam(value = "success", required = false) String success)
    {
        logger.debug("Show cart");
        cartService.updatePhonesInCart(cart);
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("shippingType", Delivery.values());
        modelMap.addAttribute("paymentType", Payment.values());
        if(success != null)
            modelMap.addAttribute("success", success);
        logger.debug("Returning cart view.");
        return "bucket";
    }

    @RequestMapping(value = "/addToCart",method = RequestMethod.GET)
    public @ResponseBody String addToCart(@RequestParam String phoneId,
                                          @ModelAttribute("cart") ArrayList<OrderItem> cart){
        logger.debug("Add to cart");
        if (cartService.addPhoneToCart(Long.parseLong(phoneId), cart))
            return "Success!";
        else
            return "Not enough on stock!";
    }

    @RequestMapping(value = "/getTotalSum")
    public @ResponseBody String getTotalSum(@ModelAttribute("cart") ArrayList<OrderItem> cart){
        logger.debug("Get total sum (cart).");
        return String.valueOf(cartService.totalSum(cart));
    }

    @RequestMapping(value = "/getQuantityPhonesInCart")
    public @ResponseBody String getQuantityPhonesInCart(@ModelAttribute("cart") ArrayList<OrderItem> cart){
        logger.debug("Get quantity phones in cart.");
        return String.valueOf(cartService.quantityPhonesInCart(cart));
    }

    @RequestMapping(value = "/clearCart")
    public @ResponseBody String clearCart(@ModelAttribute("cart") ArrayList<OrderItem> cart){
        logger.debug("Clear cart.");
        cart.clear();
        return "success";
    }

    @RequestMapping(value = "/removeOrderLine")
    public @ResponseBody String removeOrderLine(@ModelAttribute("cart") ArrayList<OrderItem> cart, @RequestParam String phoneId){
        logger.debug("Remove order line.");
        cartService.removeOrderItem(phoneService.findPhoneById(Long.parseLong(phoneId)), cart);
        return "success";
    }

    @RequestMapping(value = "/changeQuantity")
    public @ResponseBody String changeQuantity(@ModelAttribute("cart") ArrayList<OrderItem> cart,
                                               @RequestParam String phoneId,
                                               @RequestParam String newQuantity){
        logger.debug("Change quantity phone in cart.");
        int quantity = Integer.parseInt(newQuantity);
        int pricePhone = cartService.changeQuantityPhoneInCart(Long.parseLong(phoneId), cart, quantity);
        return String.valueOf(pricePhone * quantity);
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrder(@RequestParam String shipping_type,
                              @RequestParam String payment_type,
                              @RequestParam String address_name,
                              @ModelAttribute("cart") ArrayList<OrderItem> cart,
                              ModelMap modelMap) {
        logger.debug("Create order");
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Order order = new Order(Payment.getEnumValue(payment_type), Delivery.getEnumValue(shipping_type),
                StatusPayment.WAITING_FOR_PAYMENT, StatusOrder.WAITING_FOR_PAYMENT,
                new Date(System.currentTimeMillis()), cart);
        if (address_name.equals(""))
            order.setAddress("self");
        else
            order.setAddress(address_name);
        try {
            orderService.addNewOrder(order, userDetails.getUsername());
        }catch (NotEnoughException e){
            logger.error("Not enough phone." + e.getMessage());
            modelMap.addAttribute("exception", e.getMessage());
            return "error/not_enough";
        }
        cart.clear();
        logger.debug("Order has been created");
        return "redirect:/cart?success=true";
    }
}
