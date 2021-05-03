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
public class SaleReq implements Serializable {

        Long id;
        Long idProduct;
        Long idPurchaser;
        Long idSeller;
        Integer score;

}
