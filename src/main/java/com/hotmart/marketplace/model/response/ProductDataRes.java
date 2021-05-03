package com.hotmart.marketplace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataRes implements Serializable {

        Long id;
        String name;
        String description;
        LocalDateTime createdAt;
        BigDecimal score;

}
