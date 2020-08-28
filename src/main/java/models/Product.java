package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sc_products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;

    @Column(columnDefinition = "VARCHAR(64)")
    private String sku;

    @Column(columnDefinition = "VARCHAR(64)")
    private String upc;

    @Column(columnDefinition = "VARCHAR(64)")
    private String ean;

    @Column(columnDefinition = "VARCHAR(64)")
    private String jan;

    @Column(columnDefinition = "VARCHAR(64)")
    private String isbn;

    @Column(columnDefinition = "VARCHAR(64)")
    private String mpn;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String tag;

    @Column(name="meta_title", columnDefinition = "VARCHAR(255)")
    private String metaTitle;

    @Column(name="meta_description", columnDefinition = "VARCHAR(255)")
    private String metaDescription;

    @Column(name="meta_keyword", columnDefinition = "VARCHAR(255)")
    private String metaKeyword;

    @NotNull
    @Column(nullable = false, columnDefinition = "INT(4) DEFAULT 0")
    private String quantity;

    @NotNull
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean shipping;

    @NotNull
    @Column(nullable = false, columnDefinition = "DECIMAL(15,2) DEFAULT 0.00")
    private boolean price;

    @Column(name="date_available", nullable = false, columnDefinition = "DATE DEFAULT 0000-00-00")
    @Temporal(value = TemporalType.DATE)
    private java.util.Date dateAvailable;

    @Column(columnDefinition = "VARCHAR(255)")
    private String related;

    @NotNull
    @Column(nullable = false, columnDefinition = "DECIMAL(15,4) DEFAULT 0.0000")
    private double weight;

    // TODO: add weight class id FK

    @Column(columnDefinition = "DECIMAL(15,4) DEFAULT 0.0000")
    private double length;

    @Column(columnDefinition = "DECIMAL(15,4) DEFAULT 0.0000")
    private double width;

    @Column(columnDefinition = "DECIMAL(15,4) DEFAULT 0.0000")
    private double height;

    @Column(columnDefinition = "INT(11) DEFAULT 1")
    private int minimum;

    @NotNull
    @ManyToOne()
    @JoinColumn(name="stock_status_id", nullable = false, referencedColumnName = "stock_status_id")
    private StockStatus stockStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>();

    @Embedded
    private Common common;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductDiscount productDiscount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<ProductImage>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductSpecial productSpecial;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "sc_products_to_categories",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    List<Category> categories = new ArrayList<Category>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getMpn() {
        return mpn;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public boolean isPrice() {
        return price;
    }

    public void setPrice(boolean price) {
        this.price = price;
    }

    public Date getDateAvailable() {
        return dateAvailable;
    }

    public void setDateAvailable(Date dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public ProductDiscount getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscount productDiscount) {
        this.productDiscount = productDiscount;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public ProductSpecial getProductSpecial() {
        return productSpecial;
    }

    public void setProductSpecial(ProductSpecial productSpecial) {
        this.productSpecial = productSpecial;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
