package com.hotmart.marketplace.service;

import com.hotmart.marketplace.model.external.NewsApiPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("/v2/top-headlines")
    Call<NewsApiPage> topHeadlinesPage(
            @Query("apiKey") String apiKey,
            @Query("q") String q
            );

}
