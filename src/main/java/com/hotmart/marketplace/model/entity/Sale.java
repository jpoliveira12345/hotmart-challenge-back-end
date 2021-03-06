package com.hotmart.marketplace.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Audited
@AuditTable(value = "PRODUCT_AUDIT")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MARKETPLACE_SALE")
public class Sale implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_PRODUCT")
        @NotNull
        Product product;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_PURCHASER")
        @NotNull
        Purchaser purchaser;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_SELLER")
        @NotNull
        Seller seller;

        @Column(name = "SCORE")
        @Min(0)
        @Max(5)
        Integer score;

        @NotNull
        @Column(name = "CREATED_AD")
        LocalDateTime createdAt;
}
