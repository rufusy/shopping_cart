package dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"categoriesFound", "categories"})
public class ListCategoryDto {
    private final List<CategoryDto> categories;

    public ListCategoryDto(List<CategoryDto> categories) {
        this.categories = categories;
    }

    @JsonProperty("categories")
    public List<CategoryDto> getCategories(){
        return new ArrayList<>(categories);
    }

    @JsonProperty("categoriesFound")
    public boolean isCategoriesFound(){
        return true;
    }
}
