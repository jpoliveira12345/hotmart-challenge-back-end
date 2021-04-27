package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.Product;
import com.hotmart.marketplace.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService service;

    @PostMapping
    public List<Product> create(
            @RequestBody List<Product> prods) {
        return service.create(prods);
    }

    @GetMapping("/{id}")
    public Product read(
            @PathVariable(name = "id") Long idProd) {
        return service.read(idProd);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable(name = "id") Long id,
            @RequestBody Product prod) {
        return service.update(id, prod);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    public Page<Product> findAll(
            @RequestParam(name = "page", value = "1") int page,
            @RequestParam(name = "page-size", value = "20") int pageSize) {
        return service.findAll(page, pageSize);
    }
}