package com.hotmart.marketplace.model.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsApiPage {

    private String status;
    private Long totalResults;
    private List<NewsApiArticle> articles;

}
