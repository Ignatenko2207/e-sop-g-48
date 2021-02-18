package ua.mainacademy.controller.mapper;

import ua.mainacademy.controller.dto.ItemDTO;
import ua.mainacademy.model.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ItemMapper {
    public static ItemDTO toItemDTO(Item item) {
        BigDecimal price = new BigDecimal(item.getPrice()/100)
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal initPrice = new BigDecimal(item.getInitPrice()/100)
                .setScale(2, RoundingMode.HALF_UP);
        return ItemDTO.builder()
                .id(item.getId().toString())
                .itemCode(item.getItemCode())
                .name(item.getName())
                .price(price.toString())
                .initPrice(initPrice.toString())
                .build();
    }
}
