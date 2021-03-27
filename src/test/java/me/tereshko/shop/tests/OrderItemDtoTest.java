package me.tereshko.shop.tests;

import me.tereshko.shop.dto.OrderItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;


import java.io.IOException;

@JsonTest
public class OrderItemDtoTest {
    @Autowired
    private JacksonTester<OrderItemDto> jackson;

    @Test
    public void OrderItemTest() throws IOException {
         String content = "{\n" +
                 "   \"id\":1,\n" +
                 "   \"productTitle\": \"Milk\",\n" +
                 "   \"quantity\": 1,\n" +
                 "   \"pricePerProduct\": 5,\n" +
                 "   \"price\": 5\n" +
                 "}";
         OrderItemDto orderItemDto = new OrderItemDto();
         orderItemDto.setId(1L);
         orderItemDto.setProductTitle("Milk");
         orderItemDto.setQuantity(1);
         orderItemDto.setPrice(5);
         orderItemDto.setPricePerProduct(5);
         assertThat(jackson.parse(content)).isEqualTo(orderItemDto);
    }
}

