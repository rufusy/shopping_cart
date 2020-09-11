package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Model
@Entity
@Table(name="sc_categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;

    @NotNull
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name="meta_title", columnDefinition = "VARCHAR(255)")
    private String metaTitle;

    @Column(name="meta_description", columnDefinition = "VARCHAR(255)")
    private String metaDescription;

    @Column(name="meta_keyword", columnDefinition = "VARCHAR(255)")
    private String metaKeyword;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT NULL")
    private String image;

    @Embedded
    private Common common;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name="parent_id", referencedColumnName = "category_id")
    private Category parentCategory;

    @OneToMany(mappedBy="parentCategory", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Category> childCategories = new ArrayList<Category>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private List<Product> products = new ArrayList<Product>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }
}
