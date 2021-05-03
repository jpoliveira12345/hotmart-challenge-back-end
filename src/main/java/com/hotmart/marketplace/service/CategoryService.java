package com.hotmart.marketplace.service;

import com.hotmart.marketplace.constants.ExceptionResourceConstants;
import com.hotmart.marketplace.exception.MarketPlaceException;
import com.hotmart.marketplace.model.entity.Category;
import com.hotmart.marketplace.model.request.CategoryReq;
import com.hotmart.marketplace.repository.CategoryRepository;
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
public class CategoryService{
    @Autowired
    private final CategoryRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public List<Category> create(List<CategoryReq> categoryReqList) {
        var savedCategories = new ArrayList<Category>();
        for(var c : categoryReqList) {
            var category = (c.getId() != null) ? repository.findById(c.getId()) : Optional.empty();
            if (category.isPresent()) {
                throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_ALREADY_EXISTS);
            }
            var prod = mapper.map( c, Category.class);
            savedCategories.add(repository.save(prod));
        }
        return savedCategories;
    }

    public Category read(Long idProd) {
        return repository.findById(idProd).orElseThrow( () -> new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS));
    }

    public Category update(Long id, Category prod) {
        var category = repository.findById(id);
        if(category.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        return repository.save(prod);
    }

    public void delete(Long id) {
        var category = repository.findById(id);
        if(category.isEmpty()){
            throw new MarketPlaceException(ExceptionResourceConstants.RESOURCE_NOT_EXISTS);
        }

        repository.delete(category.get());
    }

    public Page<Category> findAll(@NotNull final Integer userPage, @NotNull final Integer pageSize) {
        var page = (userPage > 0) ? userPage - 1 : 1;

        var pageable = PageRequest.of(page, pageSize, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }
}