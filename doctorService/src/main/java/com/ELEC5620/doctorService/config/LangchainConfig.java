package com.ELEC5620.doctorService.config;

import com.ELEC5620.doctorService.aws.SecretsManagerUtil;
import com.ELEC5620.doctorService.tools.ApiTools;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LangchainConfig {

    @Bean
    public OpenAiChatModel chatModel() {
        String apiKey = SecretsManagerUtil.getSecret();
        
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o")
                .temperature(0.7)
                .build();
    }

    interface DoctorService {
        String ask(String question);
    }

    @Bean
    public DoctorService aiService(OpenAiChatModel chatModel, ApiTools apiTools) {
        return AiServices.builder(DoctorService.class)
                .chatLanguageModel(chatModel)
                .tools(apiTools)
                .build();
    }
}
