package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.entity.Category;
import com.hotmart.marketplace.model.request.CategoryReq;
import com.hotmart.marketplace.model.response.CategoryRes;
import com.hotmart.marketplace.service.CategoryService;
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
@RequestMapping("/categories")
@SuppressWarnings("unused")
public class CategoryController {
    @Autowired
    private final CategoryService service;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<List<CategoryRes>> create(
            @RequestBody List<CategoryReq> categoryReqs) {
        var serviceResponse = service.create(categoryReqs);
        var response = serviceResponse.stream().map( e -> mapper.map(e, CategoryRes.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryRes> read(
            @PathVariable(name = "id") Long idCateg) {
        var entity = service.read(idCateg);
        var dto = mapper.map(entity, CategoryRes.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(
            @PathVariable(name = "id") Long id,
            @RequestBody CategoryReq categoryReq) {
        var cat = mapper.map(categoryReq, Category.class);
        return new ResponseEntity<>(service.update(id, cat), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageImpl<CategoryRes>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) final Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "20", required = false) final Integer pageSize) {
        var page = service.findAll(pageNumber, pageSize);
        var categories = page.get().map( e -> mapper.map( e, CategoryRes.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(categories));
    }
}