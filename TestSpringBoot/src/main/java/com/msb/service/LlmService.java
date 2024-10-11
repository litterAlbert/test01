package com.msb.service;


import javax.servlet.http.HttpServletResponse;

public interface LlmService {
    String getllmApi(HttpServletResponse httpResponse, String question);
}
