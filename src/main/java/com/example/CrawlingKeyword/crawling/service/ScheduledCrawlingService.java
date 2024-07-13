package com.example.CrawlingKeyword.crawling.service;

import com.example.CrawlingKeyword.crawling.service.dto.NewTitlesResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduledCrawlingService {
    private final InflearnCrawlingService inflearnCrawlingService;
    private final OkkyCrawlingService okkyCrawlingService;

    private final static String keyword = "모각코";

    private static List<String> inflearnNewTitles = List.of();
    private static List<String> okkyNewTitles = List.of();

    // TODO synchronized on update period
    @Scheduled(fixedRate = 1000 * 60 * 60 * 4)
    public void crawling() {
        inflearnNewTitles = inflearnCrawlingService.updateInflearnResult();
        okkyNewTitles = okkyCrawlingService.updateOkkyResult();
        log.info("crawling finished\n" +
                new NewTitlesResponse(inflearnNewTitles, okkyNewTitles));
    }

    public NewTitlesResponse getNewTitles(){
        return new NewTitlesResponse(inflearnNewTitles, okkyNewTitles);
    }

    public NewTitlesResponse getCurrentTitles(){
        return new NewTitlesResponse(inflearnCrawlingService.getInflearnResults(), okkyCrawlingService.getOkkyResults());
    }

}
