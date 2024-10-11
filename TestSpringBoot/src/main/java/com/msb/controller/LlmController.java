package com.msb.controller;

import com.msb.service.LlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LlmController {
    @Autowired
    private LlmService llmService;
    //调用大语言模型示例
    @PostMapping("/nlp")
    public String nlp(HttpServletResponse httpResponse, @RequestBody String question){
        return llmService.getllmApi(httpResponse,question);
    }
}
