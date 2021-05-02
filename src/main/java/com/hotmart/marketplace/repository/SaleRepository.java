package com.hotmart.marketplace.repository;

import com.hotmart.marketplace.model.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value =  " SELECT AVG(s.score)" +
                    " FROM Sale s" +
                    " WHERE s.product.id = :idProd" +
                    "   AND s.createdAt > :monthsBefore")
    Optional<BigDecimal> findSaleScoreAverageScoreBefore(Long idProd, LocalDateTime monthsBefore);
}