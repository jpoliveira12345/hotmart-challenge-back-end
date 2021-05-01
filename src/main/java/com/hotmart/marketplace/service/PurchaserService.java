package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.entity.Purchaser;
import com.hotmart.marketplace.model.request.PurchaserReq;
import com.hotmart.marketplace.repository.PurchaserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaserService {
    @Autowired
    private final PurchaserRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public List<Purchaser> create(List<PurchaserReq> purchaserReqs) {
        var savedPurchasers = new ArrayList<Purchaser>();
        for(var c : purchaserReqs) {
            var purchaser = (c.getId() != null) ? repository.findById(c.getId()) : Optional.empty();
            if (purchaser.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            var purch = mapper.map( c, Purchaser.class);
            savedPurchasers.add(repository.save(purch));
        }
        return savedPurchasers;
    }

    public Purchaser read(Long idProd) {
        return repository.findById(idProd).orElseThrow( () -> new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS));
    }

    public Purchaser update(Long id, Purchaser purchaser) {
        var purch = repository.findById(id);
        if(purch.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        return repository.save(purchaser);
    }

    public void delete(Long id) {
        var purchaser = repository.findById(id);
        if(purchaser.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        repository.delete(purchaser.get());
    }

    public Page<Purchaser> findAll(@NotNull final Integer userPage, @NotNull final Integer pageSize) {
        var page = (userPage > 0) ? userPage - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }
}