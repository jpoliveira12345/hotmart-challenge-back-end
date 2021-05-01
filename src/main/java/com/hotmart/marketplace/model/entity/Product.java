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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Audited
@AuditTable(value = "PRODUCT_AUDIT")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MARKETPLACE_PRODUCT")
public class Product implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        Long id;

        @NotBlank
        @Size(max = 20)
        @Column(name = "NAME")
        String name;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ID_CATEGORY")
        Category category;

        @Column(name = "DESCRIPTION")
        String description;

        @NotNull
        @Column(name = "CREATED_AT")
        LocalDate createdAt;

        @Column(name = "SCORE")
        @Digits(integer = 10, fraction = 10)
        BigDecimal score;

}
