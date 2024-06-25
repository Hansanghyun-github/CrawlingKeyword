package com.example.CrawlingKeyword.crawling.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InflearnCrawlingService {
    private final static String url = "https://www.inflearn.com/community/studies?order=recent";
    private final static String cssQuery = ".title__text";
    private final static String keyword = "모각코";
    private final static int INFLEARN_RESULT_SIZE = 20;
    private static List<String> inflearnResults;

    @PostConstruct
    public void init() {
        try {
            Document doc = Jsoup.connect(url).get();
            inflearnResults = doc.select(cssQuery)
                    .stream().map(Element::text).toList();

            log.debug("inflearnResults: {}", inflearnResults);

            if(inflearnResults.size() != INFLEARN_RESULT_SIZE)
                log.warn("inflearn result's size is different than expected");
            else
                log.info("inflearn result setting finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> updateInflearnResult() {
        assert inflearnResults.size() == INFLEARN_RESULT_SIZE;
        List<String> nextResults = null;
        try {
            Document doc = Jsoup.connect(url).get();
            nextResults = doc.select(cssQuery)
                    .stream().map(Element::text).toList();
        } catch (Exception e){
            e.printStackTrace();
        }

        log.debug("inflearnNextResults: {}", nextResults);

        if(nextResults.size() != INFLEARN_RESULT_SIZE)
            log.warn("inflearn next results size is different than expected");

        // TODO 앞 글이 삭제되서 이전 키워드의 타이틀이 반환되는 것 제거
        // TODO -> 이전 맨 첫 글의 생성 시간 받아서 필터링
        return nextResults.stream()
                .filter(s -> !inflearnResults.contains(s))
                .filter(s -> s.contains(keyword))
                .toList();
    }
}
