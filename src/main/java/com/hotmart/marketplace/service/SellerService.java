package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.entity.Seller;
import com.hotmart.marketplace.model.request.SellerReq;
import com.hotmart.marketplace.repository.SellerRepository;
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
public class SellerService {
    @Autowired
    private final SellerRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public List<Seller> create(List<SellerReq> sellerReqList) {
        var savedSellers = new ArrayList<Seller>();
        for(var p : sellerReqList) {
            var sellerDb = (p.getId() != null) ? repository.findById(p.getId()) : Optional.empty();
            if (sellerDb.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            var seller = mapper.map( p, Seller.class);

            savedSellers.add(repository.save(seller));
        }
        return savedSellers;
    }

    public Seller read(Long idSeller) {
        return repository.findById(idSeller).orElseThrow( () -> new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS));
    }

    public Seller update(Long id, Seller seller) {
        var sellerDb = repository.findById(id);
        if(sellerDb.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        return repository.save(seller);
    }

    public void delete(Long id) {
        var seller = repository.findById(id);
        if(seller.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        repository.delete(seller.get());
    }

    public Page<Seller> findAll(@NotNull final Integer userPage, @NotNull final Integer pageSize) {
        var page = (userPage > 0) ? userPage - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }
}