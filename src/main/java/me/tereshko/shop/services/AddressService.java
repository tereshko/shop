package me.tereshko.shop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tereshko.shop.models.Address;
import me.tereshko.shop.models.Product;
import me.tereshko.shop.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    public Optional<Address> findAddressById(Long id) {
        return addressRepository.findById(id);
    }
}
