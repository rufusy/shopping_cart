package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sc_addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(128)")
    private String address_1;

    @Column(columnDefinition = "VARCHAR(128)")
    private String address_2;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(128)")
    private String primary_city;

    @Column(columnDefinition = "VARCHAR(128)")
    private String secondary_city;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private String postcode_1;

    @Column(columnDefinition = "VARCHAR(10)")
    private String postcode_2;

    @NotNull
    @OneToOne()
    @JoinColumn(name="customer_id", nullable=false, referencedColumnName = "customer_id")
    private Customer customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getPrimary_city() {
        return primary_city;
    }

    public void setPrimary_city(String primary_city) {
        this.primary_city = primary_city;
    }

    public String getSecondary_city() {
        return secondary_city;
    }

    public void setSecondary_city(String secondary_city) {
        this.secondary_city = secondary_city;
    }

    public String getPostcode_1() {
        return postcode_1;
    }

    public void setPostcode_1(String postcode_1) {
        this.postcode_1 = postcode_1;
    }

    public String getPostcode_2() {
        return postcode_2;
    }

    public void setPostcode_2(String postcode_2) {
        this.postcode_2 = postcode_2;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
