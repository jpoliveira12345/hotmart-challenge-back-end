package com.hotmart.marketplace.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReq implements Serializable {

        Long id;
        String name;
        Long idCategory;
        String description;

}
