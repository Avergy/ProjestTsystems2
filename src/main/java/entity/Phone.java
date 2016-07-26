package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;


@Entity
@Table(name = "phones")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "idBrand")
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
    @JoinColumn(name = "idColor", nullable = false)
    private Color color;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "quantityStock", nullable = false)
    private int quantityStock;

    @Column(name = "img")
    private int images;

    public Phone() {
    }

    public Phone(String name, int price, Brand brand, Color color, int weight, int quantityStock) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.quantityStock = quantityStock;
    }

    public Phone(String name, int price, int weight, int quantityStock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantityStock = quantityStock;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != phone.id) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand=" + brand +
                ", color=" + color +
                ", weight=" + weight +
                ", quantityStock=" + quantityStock +
                '}';
    }
}
