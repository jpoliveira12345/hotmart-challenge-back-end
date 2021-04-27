package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.Product;
import com.hotmart.marketplace.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    public Product create(Product prod) {
        var product =  (prod.getId() != null) ? repository.findById(prod.getId()) : Optional.empty();
        if(product.isPresent()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
        }
        return repository.save(prod);
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

    //TODO: Implementar o list com paginação do spring

}