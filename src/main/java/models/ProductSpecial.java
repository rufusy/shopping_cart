package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="sc_products_special")
public class ProductSpecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_special_id")
    private int id;

    @NotNull
    @OneToOne()
    @JoinColumn(name="product_id", nullable=false, referencedColumnName = "product_id")
    private Product product;

    @Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private boolean price;

    @Column(name = "date_start", nullable =false, columnDefinition = "DATE")
    @Temporal(value = TemporalType.DATE)
    private Date date_start;

    @Column(name = "date_end", nullable =false, columnDefinition = "DATE")
    @Temporal(value = TemporalType.DATE)
    private Date date_end;

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

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
}
