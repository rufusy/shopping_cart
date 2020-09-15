package dto.stockstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"stockFound", "stock"})
public class SingleStockStatusDto {
    private final StockStatusDto stockStatusDto;

    public SingleStockStatusDto(StockStatusDto stockStatusDto) {
        this.stockStatusDto = stockStatusDto;
    }

    @JsonProperty("stock")
    public StockStatusDto getStockStatusDto(){
        return this.stockStatusDto;
    }

    @JsonProperty("stockFound")
    public boolean isStockFound(){
        return true;
    }

}
