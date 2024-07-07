package com.example.CrawlingKeyword.crawling.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class NewTitlesResponse {
    private List<String> inflearnNewTitles;
    private List<String> okkyNewTitles;
}
