package com.hotmart.marketplace.service;

import com.hotmart.marketplace.model.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService service;

    @Test
    void numberOfNews(){
        var prod = Product.builder()
                .name("teste")
                .build();
        var result = service.numberOfNews(prod);
        Assertions.assertNotNull(result);
    }

}