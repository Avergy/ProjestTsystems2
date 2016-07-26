package entity;

/**
 * @author Alina Golubcova
 * @version 1.0
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Brand", nullable = false, unique = true)
    private String brand;


    public Brand(String brand) {
        this.brand = brand;
    }

    public Brand() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Brand)) return false;

        Brand brand1 = (Brand) o;

        if (id != brand1.id) return false;
        return brand.equals(brand1.brand);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + brand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                '}';
    }
}
