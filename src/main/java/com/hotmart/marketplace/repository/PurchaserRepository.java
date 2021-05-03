package com.hotmart.marketplace.repository;

import com.hotmart.marketplace.model.entity.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {

}