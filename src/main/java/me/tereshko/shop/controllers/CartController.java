package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import me.tereshko.shop.dto.CartDto;
import me.tereshko.shop.exceptions_handling.ResourceNotFoundException;
import me.tereshko.shop.models.Cart;
import me.tereshko.shop.services.CartService;
import me.tereshko.shop.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getCart() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("cart", new CartDto(cart));
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping("/add/{id}")
//    public void addToCart(@PathVariable Long id) {
//        cart.addToCart(id);
//    }
//
//    @GetMapping("/clear")
//    public void clearCart() {
//        cart.clear();
//    }
//
//    @GetMapping("/increment/{id}")
//    public void incrementItem(@PathVariable Long id) {
//        cart.addToCart(id);
//    }
//
//    @GetMapping("/decrement/{id}")
//    public void decrementItem(@PathVariable Long id) {
//        cart.decrementItem(id);
//    }

    @PostMapping
    public UUID createNewCart() {
        Cart cart = cartService.save(new Cart());
        return cart.getId();
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));;
        return new CartDto(cart);
    }

    @GetMapping("/{uuid}/add/{product_id}")
    public void addProductToCart(@PathVariable UUID uuid, @PathVariable(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }
}
