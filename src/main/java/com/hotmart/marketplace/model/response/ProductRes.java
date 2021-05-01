package com.hotmart.marketplace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRes implements Serializable {

        Long id;
        String name;
        Long idCategory;
        String nameCategory;
        String description;
        LocalDate createdAt;
        BigDecimal score;

}
