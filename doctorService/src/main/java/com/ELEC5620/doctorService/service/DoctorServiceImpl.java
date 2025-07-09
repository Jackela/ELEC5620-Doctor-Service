package com.ELEC5620.doctorService.service;

import com.ELEC5620.doctorService.tools.ApiTools;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final OpenAiChatModel chatModel;
    private final ApiTools apiTools;

    @Autowired
    public DoctorServiceImpl(OpenAiChatModel chatModel, ApiTools apiTools) {
        this.chatModel = chatModel;
        this.apiTools = apiTools;
    }

    @Override
    public String ask(String question) {
        DoctorService aiService = AiServices.builder(DoctorService.class)
                                            .chatLanguageModel(chatModel)
                                            .tools(apiTools)
                                            .build();
        return aiService.ask(question);
    }
}
