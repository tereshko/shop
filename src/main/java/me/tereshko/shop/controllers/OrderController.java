package me.tereshko.shop.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tereshko.shop.dto.OrderDto;
import me.tereshko.shop.dto.UserDto;
import me.tereshko.shop.dto.jwt.JWTResponse;
import me.tereshko.shop.exceptions_handling.ResourceNotFoundException;
import me.tereshko.shop.models.Address;
import me.tereshko.shop.models.User;
import me.tereshko.shop.services.AddressService;
import me.tereshko.shop.services.OrderService;
import me.tereshko.shop.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final AddressService addressService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal, @RequestBody Address address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user, address);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

//    @GetMapping
//    public ResponseEntity<?> getCurrentUserOrders(Principal principal) {
//
//        Map<String, Object> response = new HashMap<>();
//        List<OrderDto> orderDto = orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
//
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        Optional<Address> address = addressService.findAddressById(orderDto.getAddress_id());
//
//
//
//        response.put("order", );
//        response.put("address", new Address(user));
//
//        return ResponseEntity.ok(response);
//    }
}
