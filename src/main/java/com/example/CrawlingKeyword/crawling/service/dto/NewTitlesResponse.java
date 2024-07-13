package com.example.CrawlingKeyword.crawling.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewTitlesResponse {
    private List<String> inflearnNewTitles;
    private List<String> okkyNewTitles;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("InflearnNewTitles: {\n");
        for(String title : inflearnNewTitles)
            builder.append("\t").append(title).append("\n");
        builder.append("}\n");

        builder.append("OkkyNewTitles: {\n");
        for(String title : okkyNewTitles)
            builder.append("\t").append(title).append("\n");
        builder.append("}\n");

        return builder.toString();
    }
}
