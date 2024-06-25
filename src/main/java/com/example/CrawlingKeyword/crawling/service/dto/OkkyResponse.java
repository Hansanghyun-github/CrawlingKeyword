package com.example.CrawlingKeyword.crawling.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OkkyResponse {
    private Props props;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Props {
        private PageProps pageProps;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PageProps {
        private Result result;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        @JsonProperty("content")
        private List<Content> contents;
    }

    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        private String title;
    }

    public List<String> getTitles(){
        return this.getProps()
                .getPageProps()
                .getResult()
                .getContents()
                .stream().map(Content::getTitle).toList();
    }
}
