package com.msb.config;

import com.baidubce.qianfan.Qianfan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class LlmConfig {
    private String accessKey="";

    private String secretKey="";

    private Qianfan qianfan;

    private String beginPrompt="";
}
