package com.hotmart.marketplace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketPlacePage <T> {

    private LocalDate currentDate;
    private String queryTerm;
    private List<T> data;
}
