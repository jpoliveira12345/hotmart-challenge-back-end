package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.entity.Sale;
import com.hotmart.marketplace.model.request.SaleReq;
import com.hotmart.marketplace.repository.ProductRepository;
import com.hotmart.marketplace.repository.PurchaserRepository;
import com.hotmart.marketplace.repository.SaleRepository;
import com.hotmart.marketplace.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SaleService {
    @Autowired
    private final SaleRepository repository;

    @Autowired
    private final PurchaserRepository purchaserRepository;

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ModelMapper mapper;

    public List<Sale> create(List<SaleReq> saleReqList) {
        var savedSales = new ArrayList<Sale>();
        for(var p : saleReqList) {
            var saleDb = (p.getId() != null) ? repository.findById(p.getId()) : Optional.empty();
            if (saleDb.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            var purchaser = purchaserRepository.findById(p.getIdPurchaser()).orElse(null);
            var product = productRepository.findById(p.getIdProduct()).orElse(null);
            var seller = sellerRepository.findById(p.getIdSeller()).orElse(null);
            var sale = mapper.map( p, Sale.class);

            sale.setPurchaser(purchaser);
            sale.setProduct(product);
            sale.setSeller(seller);
            sale.setCreatedAt(LocalDateTime.now());
            savedSales.add(repository.save(sale));
        }
        return savedSales;
    }

    public Sale read(Long idSale) {
        return repository.findById(idSale).orElseThrow( () -> new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS));
    }

    public Sale update(Long id, Sale sale) {
        var saleDb = repository.findById(id);
        if(saleDb.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        return repository.save(sale);
    }

    public void delete(Long id) {
        var sale = repository.findById(id);
        if(sale.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        repository.delete(sale.get());
    }

    public Page<Sale> findAll(@NotNull final Integer userPage, @NotNull final Integer pageSize) {
        var page = (userPage > 0) ? userPage - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return repository.findAll(pageable);
    }
}