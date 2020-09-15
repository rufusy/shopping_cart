package dto.stockstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"stocksFound", "stocks"})
public class ListStockStatusDto {

    private final List<StockStatusDto> stockStatusDtoList;

    public ListStockStatusDto(List<StockStatusDto> stockStatusDtoList) {
        this.stockStatusDtoList = stockStatusDtoList;
    }

    @JsonProperty("stocks")
    public List<StockStatusDto> getStockStatusDtoList() {
        return new ArrayList<>(stockStatusDtoList);
    }

    @JsonProperty("stocksFound")
    public boolean isStocksFound(){
        return true;
    }
}
