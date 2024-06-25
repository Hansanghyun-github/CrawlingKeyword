package com.example.CrawlingKeyword.crawling.service;

import com.example.CrawlingKeyword.crawling.service.dto.OkkyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OkkyCrawlingService {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String url = "https://okky.kr/community/gathering?sort=ID";
    private final static String elementId = "__NEXT_DATA__";
    private final static String keyword = "모각코";
    private final static int OKKY_RESULT_SIZE = 20;
    private static List<String> okkyResults;

    // todo use @Scheduled
    /**
     *
     *
     * */

    @PostConstruct
    public void init() {
        try {
            Document doc = Jsoup.connect(url).get();
            String html = doc.getElementById(elementId).html();

            OkkyResponse okkyResponse = mapper.readValue(html, OkkyResponse.class);

            okkyResults = okkyResponse.getTitles();

            log.debug("okkyResults: {}", okkyResults);

            if(okkyResults.size() != OKKY_RESULT_SIZE)
                log.warn("okky result's size is different than expected");
            else
                log.info("okky result setting finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> updateOkkyResult() {
        assert okkyResults.size() == OKKY_RESULT_SIZE;
        List<String> nextResults = null;
        try {
            Document doc = Jsoup.connect(url).get();
            String html = doc.getElementById(elementId).html();
            OkkyResponse okkyResponse = mapper.readValue(html, OkkyResponse.class);
            nextResults = okkyResponse.getTitles();
        } catch (Exception e){
            e.printStackTrace();
        }

        log.debug("okkyNextResults: {}", nextResults);

        if(nextResults.size() != OKKY_RESULT_SIZE)
            log.warn("okky next results size is different than expected");

        // TODO 앞 글이 삭제되서 이전 키워드의 타이틀이 반환되는 것 제거
        return nextResults.stream()
                .filter(s -> !okkyResults.contains(s))
                .filter(s -> s.contains(keyword))
                .toList();

        // todo okkyResults update
    }
}
