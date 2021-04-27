package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.Product;
import com.hotmart.marketplace.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    public List<Product> create(List<Product> prods) {
        var savedProducts = new ArrayList<Product>();
        for(var p : prods) {
            var product = (p.getId() != null) ? repository.findById(p.getId()) : Optional.empty();
            if (product.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            savedProducts.add(repository.save(p));
        }
        return savedProducts;
    }

    public Product read(Long idProd) {
        return repository.findById(idProd).orElseThrow( () -> new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS));
    }

    public Product update(Long id, Product prod) {
        var product = repository.findById(id);
        if(product.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        return repository.save(prod);
    }

    public void delete(Long id) {
        var product = repository.findById(id);
        if(product.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        repository.delete(product.get());
    }

    public Page<Product> findAll(int page, int pageSize) {
        page = (page > 0) ? page - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }

}