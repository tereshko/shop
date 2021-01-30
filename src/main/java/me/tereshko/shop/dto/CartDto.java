package me.tereshko.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.tereshko.shop.beans.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderItemDto> itemDtos;
    private double totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.itemDtos = cart.getItems().stream()
                .map(OrderItemDto::new)
                .collect(Collectors.toList());
    }
}
