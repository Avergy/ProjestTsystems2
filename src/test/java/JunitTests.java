import dao.interfaces.OrderDao;
import dao.interfaces.PhoneDao;
import dao.interfaces.UserDao;
import entity.*;
import exceptions.NotEnoughException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import services.implementations.*;
import services.interfaces.BrandService;
import services.interfaces.OrderService;

import javax.jws.soap.SOAPBinding;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class JunitTests {

    private OrderServiceImpl orderService;
    private PhoneServiceImpl phoneService;
    private UserServiceImpl userService;
    private CartService cartService;
    private UserDao userDao;
    private OrderDao orderDao;
    private PhoneDao phoneDao;


    private Order getOrder()
    {
        Order order = new Order();
        List<OrderItem> list = new LinkedList<>();
        OrderItem orderItem = new OrderItem();
        Phone phone = new Phone();
        phone.setId(1);
        orderItem.setPhone(phone);
        orderItem.setQuantity(100);
        list.add(orderItem);
        order.setOrderItems(list);
        return order;
    }

    private Phone getPhone(long id) {
        Phone phone = new Phone();
        phone.setId(id);
        phone.setQuantityStock(10);
        phone.setPrice((int) (id * 2));
        return phone;
    }

    private User getUser(long id)
    {
        User user = new User();
        user.setId(id);
        return user;
    }

    private List<OrderItem> getCart()
    {
        List<OrderItem> list = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setPhone(getPhone(1));
        orderItem.setQuantity(1);
        list.add(orderItem);
        orderItem = new OrderItem();
        orderItem.setPhone(getPhone(2));
        orderItem.setQuantity(2);
        list.add(orderItem);
        orderItem = new OrderItem();
        orderItem.setPhone(getPhone(3));
        orderItem.setQuantity(1);
        list.add(orderItem);
        return list;
    }

    @Before
    public void set(){
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl();
        phoneService = new PhoneServiceImpl();
        userService = new UserServiceImpl();
        cartService = new CartService();
        orderDao = Mockito.mock(OrderDao.class);
        phoneDao = Mockito.mock(PhoneDao.class);
        userDao = Mockito.mock(UserDao.class);
        phoneService.setPhoneDao(phoneDao);
        userService.setUserDao(userDao);
        orderService.setPhoneService(phoneService);
        cartService.setPhoneService(phoneService);
    }

    @Test
    public void testTopTenPhones(){

        Map<Phone, Integer> map = new HashMap<>();
        for(int i = 1; i < 11; i++)
        {
            map.put(getPhone(i), 30 + i);
        }
        Mockito.doReturn(map).when(phoneDao).getTopTenPhones();
        Map<Phone, Integer> topTenPhones = phoneService.getTopTenPhones();
        long i = 100;
        boolean flag = true;
        for (Map.Entry<Phone, Integer> entry: topTenPhones.entrySet()){
            if (entry.getValue() <= i)
                i = entry.getValue();
            else {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void testTopTenUsers(){

        Map<User, Long> map = new HashMap<>();
        for(int i = 1; i < 11; i++)
        {
            map.put(getUser(i), (long) (30 + i));
        }
        Mockito.doReturn(map).when(userDao).getTopTenUsers();
        Map<User, Long> topTenUsers = userService.getTopTenUsers();
        long i = 100;
        boolean flag = true;
        for (Map.Entry<User, Long> entry: topTenUsers.entrySet()){
            if (entry.getValue() <= i)
                i = entry.getValue();
            else {
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag);
    }

    @Test(expected = NotEnoughException.class)
    public void testCreateOrder() throws NotEnoughException {
        Mockito.doReturn(getPhone(1)).when(phoneDao).findById(1);
        orderService.addNewOrder(getOrder(),"Test");
    }

    @Test
    public void testRemoveOrderItem()
    {
        List<OrderItem> list = getCart();
        List<OrderItem> cart = getCart();
        cartService.removeOrderItem(getPhone(2), cart);
        Assert.assertTrue(list.size() - cart.size() == 1);
        Assert.assertTrue(!cart.contains(getPhone(2)));
    }

    @Test
    public void testQuantityPhonesInCart()
    {
        int test_quantity = cartService.quantityPhonesInCart(getCart());
        Assert.assertEquals(4, test_quantity);
    }

    @Test
    public void testTotalSummCart()
    {
        Mockito.doReturn(getPhone(1)).when(phoneDao).findById(1);
        Mockito.doReturn(getPhone(2)).when(phoneDao).findById(2);
        Mockito.doReturn(getPhone(3)).when(phoneDao).findById(3);
        int total = (int) cartService.totalSum(getCart());
        Assert.assertEquals(16, total);
    }

}
