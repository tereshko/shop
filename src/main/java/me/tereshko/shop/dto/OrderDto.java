package me.tereshko.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.tereshko.shop.models.Order;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String username;
    private double totalPrice;
    private String address;
    private String creationDateTime;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.username = order.getOwner().getUsername();
        this.totalPrice = order.getPrice();
        this.address = order.getAddress_id().toString();
        this.creationDateTime = order.getCreatedAt().toString();
    }

}