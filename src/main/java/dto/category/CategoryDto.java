package dto.category;

import models.Category;

public class CategoryDto {
    private final String id;
    private final String name;
    private final String description;
    private final String metaTitle;
    private final String metaDescription;
    private final String metaKeyword;
    private final String image;
    private final String status;
    private final String creationDate;
    private final String modifiedDate;
    private final String parentName;

    public CategoryDto(Category category){
        this.id = String.valueOf(category.getId());
        this.name = category.getName();
        this.description = category.getDescription();
        this.metaTitle = category.getMetaTitle();
        this.metaDescription = category.getMetaDescription();
        this.metaKeyword = category.getMetaKeyword();
        this.image = category.getImage();
        this.status = category.getCommon().status();
        this.creationDate = category.getCommon().creationDate();
        this.modifiedDate = category.getCommon().modifiedDate();
        this.parentName = category.getParentName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getParentName() {
        return parentName;
    }
}
