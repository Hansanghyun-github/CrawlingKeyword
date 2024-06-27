package com.example.CrawlingKeyword.crawling.service.dto;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class NewTitlesResponse {
    private List<String> inflearnNewTitles;
    private List<String> okkyNewTitles;
}
