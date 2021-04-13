package me.tereshko.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.tereshko.shop.models.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<CartItemDto> itemDtos;
    private double totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getPrice();
        this.itemDtos = cart.getItems().stream().map(CartItemDto::new).collect(Collectors.toList());
    }
}
