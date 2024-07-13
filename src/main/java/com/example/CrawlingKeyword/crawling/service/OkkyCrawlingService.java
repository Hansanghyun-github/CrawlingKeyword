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
    private static List<String> okkyResults = List.of();

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

        if(nextResults == null)
            throw new RuntimeException("okky next results is null");

        log.debug("okkyNextResults: {}", nextResults);

        if(nextResults.size() != OKKY_RESULT_SIZE)
            log.warn("okky next results size is different than expected");

        // TODO 앞 글이 삭제되서 이전 키워드의 타이틀이 반환되는 것 제거
        List<String> list = nextResults.stream()
                .filter(s -> !okkyResults.contains(s))
                .filter(s -> s.contains(keyword))
                .toList();

        okkyResults = nextResults;

        return list;

        // todo okkyResults update
    }

    public List<String> getOkkyResults() {
        return okkyResults;
    }
}
