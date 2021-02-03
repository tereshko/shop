package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.shop.beans.Cart;
import me.tereshko.shop.dto.CartDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final Cart cart;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCart() {
        Map<String, Object> response = new HashMap<>();
        response.put("cart", new CartDto(cart));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

    @GetMapping("/increment/{id}")
    public void incrementItem(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/decrement/{id}")
    public void decrementItem(@PathVariable Long id) {
        cart.decrementItem(id);
    }
}
