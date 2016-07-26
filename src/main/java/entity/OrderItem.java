package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitems")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idOrder", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPhone", nullable = false)
    private Phone phone;

    @Column(name = "quantity")
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem item = (OrderItem) o;

        if (id != item.id) return false;
        if (quantity != item.quantity) return false;
        if (order != null ? !order.equals(item.order) : item.order != null) return false;
        return phone.equals(item.phone);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + phone.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", phone=" + phone +
                ", quantity=" + quantity +
                '}';
    }
}
