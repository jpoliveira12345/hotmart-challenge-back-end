package com.hotmart.marketplace.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@Audited
@AuditTable(value = "PRODUCT_AUDIT")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MARKETPLACE_SELLER")
public class Seller implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        Long id;

        @NotBlank
        @Column(name = "NAME")
        @Size(max = 20)
        String name;

}
