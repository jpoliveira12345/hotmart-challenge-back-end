package com.hotmart.marketplace.api;

import com.hotmart.marketplace.model.entity.Purchaser;
import com.hotmart.marketplace.model.request.PurchaserReq;
import com.hotmart.marketplace.model.response.PurchaserRes;
import com.hotmart.marketplace.service.PurchaserService;
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
@RequestMapping("/purchasers")
@SuppressWarnings("unused")
public class PurchaserController {
    @Autowired
    private final PurchaserService service;

    @Autowired
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<List<PurchaserRes>> create(
            @RequestBody List<PurchaserReq> purchReqList) {
        var serviceResponse = service.create(purchReqList);
        var response = serviceResponse.stream().map( e -> mapper.map(e, PurchaserRes.class)).collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaserRes> read(
            @PathVariable(name = "id") Long idProd) {
        var entity = service.read(idProd);
        var dto = mapper.map(entity, PurchaserRes.class);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchaser> update(
            @PathVariable(name = "id") Long id,
            @RequestBody PurchaserReq purchReq) {
        var purch = mapper.map(purchReq, Purchaser.class);
        return new ResponseEntity<>(service.update(id, purch), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable(name = "id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<PageImpl<PurchaserRes>> findAll(
            @RequestParam(name = "page", defaultValue = "1", required = false) final Integer pageNumber,
            @RequestParam(name = "page-size", defaultValue = "20", required = false) final Integer pageSize) {
        var page = service.findAll(pageNumber, pageSize);
        var purchaser = page.get().map( e -> mapper.map( e, PurchaserRes.class)).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(purchaser));
    }
}