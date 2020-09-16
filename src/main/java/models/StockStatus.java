package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sc_stock_status")
public class StockStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="stock_status_id")
    private int id;

    @Column(nullable = false, columnDefinition = "VARCHAR(32)")
    private String name;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean status;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean deleted;

    @JsonManagedReference
    @OneToMany(mappedBy = "stockStatus",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Product> productList = new ArrayList<Product>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
