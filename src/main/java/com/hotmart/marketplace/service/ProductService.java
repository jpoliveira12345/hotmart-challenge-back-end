package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.entity.Product;
import com.hotmart.marketplace.model.request.ProductReq;
import com.hotmart.marketplace.repository.CategoryRepository;
import com.hotmart.marketplace.repository.ProductRepository;
import com.hotmart.marketplace.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final SaleRepository saleRepository;

    @Autowired
    private final ModelMapper mapper;

    public List<Product> create(List<ProductReq> productReqList) {
        var savedProducts = new ArrayList<Product>();
        for(var p : productReqList) {
            var product = (p.getId() != null) ? repository.findById(p.getId()) : Optional.empty();
            if (product.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            var category = categoryRepository.findById(p.getIdCategory()).orElse(null);
            var prod = mapper.map( p, Product.class);

            prod.setCategory(category);
            prod.setCreatedAt(LocalDateTime.now());
            savedProducts.add(repository.save(prod));
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

    public Page<Product> findAll(@NotNull final Integer userPage, @NotNull final Integer pageSize) {
        var page = (userPage > 0) ? userPage - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }

    public BigDecimal averageScoreInMonths(@NotNull final Long idProd, @NotNull final Integer months){
        var monthsBefore = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).minusMonths(months);
        return saleRepository.findSaleScoreAverageScoreBefore(idProd, monthsBefore).orElse(BigDecimal.ZERO);
    }

    public BigDecimal sellsByDay(@NotNull final Product prod){
        var createdAt = prod.getCreatedAt().toLocalDate();
        var prodAgeDays = new BigDecimal(Period.between( LocalDate.now() , createdAt ).getDays());
        var nSales = new BigDecimal(saleRepository.countByProductId(prod.getId()));

        if(prodAgeDays.equals(BigDecimal.ZERO)){
            return BigDecimal.ZERO;
        }

        return nSales.divide(prodAgeDays, RoundingMode.HALF_EVEN);
    }

}