package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="sc_products_discount")
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_discount_id")
    private int id;

    @NotNull
    @OneToOne()
    @JoinColumn(name="product_id", nullable=false, referencedColumnName = "product_id")
    private Product product;

    @Column(nullable = false, columnDefinition = "INT(4) DEFAULT 0")
    private String quantity;

    @Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private boolean price;

    @Column(name = "date_start", nullable =false, columnDefinition = "DATE")
    @Temporal(value = TemporalType.DATE)
    private Date dateStart;

    @Column(name = "date_end", nullable =false, columnDefinition = "DATE")
    @Temporal(value = TemporalType.DATE)
    private Date dateEnd;

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
