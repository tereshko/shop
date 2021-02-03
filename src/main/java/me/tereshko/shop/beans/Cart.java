package me.tereshko.shop.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.tereshko.shop.exceptions_handling.ResourceNotFoundException;
import me.tereshko.shop.models.OrderItem;
import me.tereshko.shop.models.Product;
import me.tereshko.shop.services.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
//@Scope("singleton")
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private double totalPrice;

    @PostConstruct
    private void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculateCart();
                return;
            }
        }
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(product);
        items.add(orderItem);
        recalculateCart();
    }

    public void recalculateCart() {
        totalPrice = 0;
        for (OrderItem o: items) {
            totalPrice = totalPrice + o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        recalculateCart();
    }

    public void decrementItem(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.decrementQuantity();
                recalculateCart();
                return;
            }
        }
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(product);
        items.remove(orderItem);
        recalculateCart();
    }
}
