package com.example.CrawlingKeyword.crawling.controller;

import com.example.CrawlingKeyword.crawling.service.ScheduledCrawlingService;
import com.example.CrawlingKeyword.crawling.service.dto.NewTitlesResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CrawlingController {

    private final ScheduledCrawlingService scheduledCrawlingService;

    @Value("${secret.key}")
    private String SECRET_KEY;

    @GetMapping("/titles/new")
    public NewTitlesResponse get(@RequestParam("secretKey") String secretKey, HttpServletResponse response){
        if(!SECRET_KEY.equals(secretKey)){
            log.warn("invalid secret key");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        NewTitlesResponse newTitles = scheduledCrawlingService.getNewTitles();
        log.info("newTitles: \n{}", newTitles);
        return newTitles;
    }

    @GetMapping("/titles/current")
    public NewTitlesResponse getCurrentTitles(@RequestParam("secretKey") String secretKey, HttpServletResponse response){
        if(!SECRET_KEY.equals(secretKey)){
            log.warn("invalid secret key");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        NewTitlesResponse currentTitles = scheduledCrawlingService.getCurrentTitles();
        log.info("currentTitles: \n{}", currentTitles);
        return currentTitles;
    }
}
