package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.entity.Sale;
import com.hotmart.marketplace.model.request.SaleReq;
import com.hotmart.marketplace.model.response.SaleRes;
import com.hotmart.marketplace.service.SaleService;
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
@RequestMapping("/sales")
@SuppressWarnings("unused")
public class SaleController {
    @Autowired
    private final SaleService service;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<List<SaleRes>> create(
            @RequestBody List<SaleReq> saleReqList) {
        var serviceResponse = service.create(saleReqList);
        var response = serviceResponse.stream().map( e -> mapper.map(e, SaleRes.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleRes> read(
            @PathVariable(name = "id") Long idSale) {
        var entity = service.read(idSale);
        var dto = mapper.map(entity, SaleRes.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(
            @PathVariable(name = "id") Long id,
            @RequestBody SaleReq saleReq) {
        var sale = mapper.map(saleReq, Sale.class);
        return new ResponseEntity<>(service.update(id, sale), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageImpl<SaleRes>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) final Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "20", required = false) final Integer pageSize) {
        var page = service.findAll(pageNumber, pageSize);
        var sales = page.get().map( e -> mapper.map( e, SaleRes.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(sales));
    }
}