package models;

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

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(32)")
    private String name;

    @OneToMany(mappedBy = "stockStatus", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
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
}
