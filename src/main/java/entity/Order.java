package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import enums.Delivery;
import enums.Payment;
import enums.StatusOrder;
import enums.StatusPayment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "idUser")
    private User user;

    @JoinColumn(name = "Address")
    private String Address;

    @Column(name = "Payment", nullable = false)
    @Enumerated(EnumType.STRING)
    private Payment payment;

    @Column(name = "Delivery", nullable = false)
    @Enumerated(EnumType.STRING)
    private Delivery delivery;

    @Column(name = "StatusPayment", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

   @Column(name = "StatusOrder", nullable = false)
   @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "Cost", nullable = false)
    private long cost;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(Payment payment, Delivery delivery, StatusPayment statusPayment,
                 StatusOrder statusOrder, Date date, List<OrderItem> orderItems) {
        this.payment = payment;
        this.delivery = delivery;
        this.statusPayment = statusPayment;
        this.statusOrder = statusOrder;
        this.date = date;
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public StatusPayment getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(StatusPayment statusPayment) {
        this.statusPayment = statusPayment;
    }

    public StatusOrder getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(StatusOrder statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (cost != order.cost) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (Address != null ? !Address.equals(order.Address) : order.Address != null) return false;
        if (payment != order.payment) return false;
        if (delivery != order.delivery) return false;
        if (statusPayment != order.statusPayment) return false;
        if (statusOrder != order.statusOrder) return false;
        return date != null ? date.equals(order.date) : order.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (Address != null ? Address.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (statusPayment != null ? statusPayment.hashCode() : 0);
        result = 31 * result + (statusOrder != null ? statusOrder.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) (cost ^ (cost >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", Address=" + Address +
                ", payment=" + payment +
                ", delivery=" + delivery +
                ", statusPayment=" + statusPayment +
                ", statusOrder=" + statusOrder +
                ", date=" + date +
                ", cost=" + cost +
                '}';
    }
}
