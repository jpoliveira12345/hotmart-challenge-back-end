package com.hotmart.marketplace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRes implements Serializable {

        Long id;
        Long idProduct;
        String productName;
        Long idPurchaser;
        String purchaserName;
        Long idSeller;
        String sellerName;
        Integer score;

}
