package com.example.CrawlingKeyword.crawling.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CrawlingController {

    /**
     * 지금은 http 요청이 올 때마다 새로 업데이트 해서,
     * 새로운 글 반환 한다.
     *
     * -> TODO 주기적으로 직접 업데이트 하고 (@Scheduled 이용), 요청 올때 새로운 글 알려주는 아키텍쳐로 변경
     * */

    @GetMapping("/titles/new")
    public void get(){
        // TOOD 쿼리 파라미터로 시크릿 키를 받자, 안되면 404
    }
}
