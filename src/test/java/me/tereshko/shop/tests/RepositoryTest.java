package me.tereshko.shop.tests;

import me.tereshko.shop.models.Product;
import me.tereshko.shop.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void initDbTest() {
        List<Product> genresList = productRepository.findAll();
        Assertions.assertEquals(3, genresList.size());
    }

    @Test
    public void initPrice() {
        Product p = productRepository.getOne(1L);
        double price = p.getPrice();
        Assertions.assertEquals(24L, price);
    }

    @Test
    public void initTitle() {
        Product p = productRepository.getOne(1L);
        String title = p.getTitle();
        Assertions.assertEquals("Bread", title);
    }
}
