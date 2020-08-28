package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sc_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private int id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="product_id", nullable = false, referencedColumnName = "product_id")
    private Product product;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="customer_id", nullable = false, referencedColumnName = "customer_id")
    private Customer customer;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(64)")
    private String author;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @NotNull
    @Column(nullable = false, columnDefinition = "INT(1)")
    private int rating;

    @Embedded
    private Common common;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }
}
