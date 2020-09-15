package dto.stockstatus;

import models.StockStatus;

public class StockStatusDto {
    private final String id;
    private final String name;

    public StockStatusDto(StockStatus stockStatus) {
        this.id = String.valueOf(stockStatus.getId());
        this.name = stockStatus.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
