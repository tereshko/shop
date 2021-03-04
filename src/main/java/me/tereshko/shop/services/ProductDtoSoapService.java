package me.tereshko.shop.services;

import lombok.RequiredArgsConstructor;
import me.tereshko.shop.models.Product;
import me.tereshko.shop.repositories.ProductRepository;
import me.tereshko.shop.soap.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDtoSoapService {
    private final ProductRepository productRepository;

    public static final Function<Product, ProductDto> functionEntityToSoap = se -> {
        ProductDto s = new ProductDto();
        s.setId(se.getId());
        s.setName(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public List<ProductDto> getAllProductsDto() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductDto getProductDtoById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}
