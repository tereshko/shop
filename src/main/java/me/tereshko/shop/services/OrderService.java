package me.tereshko.shop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tereshko.shop.beans.Cart;
import me.tereshko.shop.models.Address;
import me.tereshko.shop.models.Order;
import me.tereshko.shop.models.User;
import me.tereshko.shop.repositories.AddressRepository;
import me.tereshko.shop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;
    private final AddressRepository addressRepository;

    public Order createFromUserCart(User user, Address address) {
        Order order = new Order(cart, user);
        if (!(order.getItems().size() == 0)) {
            address = addressRepository.save(address);

            order.setAddress_id(address);
            orderRepository.save(order);

            cart.clear();
        }

        return order;
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
