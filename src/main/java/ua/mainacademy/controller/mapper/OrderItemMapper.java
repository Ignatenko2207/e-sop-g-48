package ua.mainacademy.controller.mapper;

import ua.mainacademy.controller.dto.OrderItemDTO;
import ua.mainacademy.model.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderItemMapper {

    public static OrderItemDTO toOrderItemDTO (OrderItem orderItem) {
        BigDecimal price = new BigDecimal(orderItem.getItem().getPrice()/100)
                .setScale(2, RoundingMode.HALF_UP);
        return OrderItemDTO.builder()
                .itemCode(orderItem.getItem().getItemCode())
                .itemName(orderItem.getItem().getName())
                .price(price.toString())
                .amount(orderItem.getAmount())
                .build();
    }
}
