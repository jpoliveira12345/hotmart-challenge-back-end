package com.hotmart.marketplace.repository;

import com.hotmart.marketplace.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Long, Category> {

}