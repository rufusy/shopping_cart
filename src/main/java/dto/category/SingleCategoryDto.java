package dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"categoryFound", "category"})
public class SingleCategoryDto {
    private final CategoryDto category;

    public SingleCategoryDto(CategoryDto category){
        this.category = category;
    }

    @JsonProperty("category")
    public CategoryDto getCategory() {
        return this.category;
    }


    @JsonProperty("categoryFound")
    public boolean isUserFound(){
        return true;
    }

}
