package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.entity.Seller;
import com.hotmart.marketplace.model.request.SellerReq;
import com.hotmart.marketplace.model.response.SellerRes;
import com.hotmart.marketplace.service.SellerService;
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
@RequestMapping("/sellers")
@SuppressWarnings("unused")
public class SellerController {
    @Autowired
    private final SellerService service;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<List<SellerRes>> create(
            @RequestBody List<SellerReq> sellerReqList) {
        var serviceResponse = service.create(sellerReqList);
        var response = serviceResponse.stream().map( e -> mapper.map(e, SellerRes.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerRes> read(
            @PathVariable(name = "id") Long idSeller) {
        var entity = service.read(idSeller);
        var dto = mapper.map(entity, SellerRes.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> update(
            @PathVariable(name = "id") Long id,
            @RequestBody SellerReq sellerReq) {
        var seller = mapper.map(sellerReq, Seller.class);
        return new ResponseEntity<>(service.update(id, seller), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageImpl<SellerRes>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) final Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "20", required = false) final Integer pageSize) {
        var page = service.findAll(pageNumber, pageSize);
        var sellers = page.get().map( e -> mapper.map( e, SellerRes.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(sellers));
    }
}