package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.entity.Product;
import com.hotmart.marketplace.model.request.ProductReq;
import com.hotmart.marketplace.model.response.ProductRes;
import com.hotmart.marketplace.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
@SuppressWarnings("unused")
public class ProductController {
    @Autowired
    private final ProductService service;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<List<ProductRes>> create(
            @RequestBody List<ProductReq> prodReqList) {
        var serviceResponse = service.create(prodReqList);
        var response = serviceResponse.stream().map( e -> mapper.map(e, ProductRes.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> read(
            @PathVariable(name = "id") Long idProd) {
        var entity = service.read(idProd);
        var dto = mapper.map(entity, ProductRes.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable(name = "id") Long id,
            @RequestBody ProductReq prodReq) {
        var prod = mapper.map(prodReq, Product.class);
        return new ResponseEntity<>(service.update(id, prod), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageImpl<ProductRes>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) final Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "20", required = false) final Integer pageSize) {
        var page = service.findAll(pageNumber, pageSize);
        var products = page.get().map( e -> mapper.map( e, ProductRes.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(products));
    }
}