package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sc_products_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_image_id")
    private int id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
