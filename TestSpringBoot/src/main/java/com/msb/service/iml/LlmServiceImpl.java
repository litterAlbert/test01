package com.msb.service.iml;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.msb.service.LlmService;
import com.msb.service.LlmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msb.config.LlmConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class LlmServiceImpl implements LlmService {

    @Autowired
    private LlmConfig config;
    // 存储会话ID和对话历史
    private Map<String, StringBuilder> conversationMap = new HashMap<>();

    // 默认会话ID
    private static final String DEFAULT_SESSION_ID = "123";

    @Override
    public String getllmApi(HttpServletResponse httpResponse, String question) {
        Qianfan qianfan = new Qianfan(config.getAccessKey(), config.getSecretKey());
        if (config.getQianfan() == null) {
            config.setQianfan(qianfan);
        }
        // 确保会话ID为指定的默认ID
        String sessionId = DEFAULT_SESSION_ID;
        conversationMap.putIfAbsent(sessionId, new StringBuilder());

        // 获取会话的对话历史
        StringBuilder conversationHistory = conversationMap.get(sessionId);

        // 构建 prompt
        String prompt = config.getBeginPrompt()+conversationHistory.toString() + "user: " + question + "\n";

        // 初始化 ChatCompletion 对象
        ChatResponse response = config.getQianfan().chatCompletion()
                .model("ERNIE-Speed-8K")
                .addMessage("user", prompt)
                .execute();

        // 获取模型的响应
        String result = response.getResult();

        // 更新对话历史
        conversationHistory.append(question).append("\n");
        conversationHistory.append(result).append("\n");

        // 打印对话记录
        System.out.println(conversationHistory);

        // 返回对话结果
        return result;
    }
}
