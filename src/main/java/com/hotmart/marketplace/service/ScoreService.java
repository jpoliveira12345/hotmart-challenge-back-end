package com.hotmart.marketplace.service;

import com.hotmart.marketplace.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductService productService;

    @Scheduled(cron = "0 0 1 * * *")
    private void calculateProductScore(){
        var products = productRepository.findAll();
        for(var p : products){
            var x = productService.averageScoreInMonths(p.getId(), 12);
            var y = productService.sellsByDay(p);
            var z = productService.numberOfNews(p);

            p.setScore(x.add(y).add(z));

            productRepository.save(p);
        }
    }

}