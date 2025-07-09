package com.ELEC5620.doctorService.ai;

import com.ELEC5620.doctorService.aws.SecretsManagerUtil;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.HashMap;
import java.util.Map;

public class OpenAIModelFactory {

    private static final String MODEL_NAME = "gpt-4o";
    private static final double TEMPERATURE = 0.7;
    private static final int MAX_TOKENS = 150;
    private static final Map<String, String> PROMPTS = new HashMap<>();
    private static final String DEFAULT_PROMPT = "You are a medical assistant capable of engaging in conversations about various health-related topics. Please provide a helpful response to the following query: %s";

    static {
        initializePrompts();
    }

    private static void initializePrompts() {
        PROMPTS.put("HEALTH_ADVICE", "You are a health advisor. Based on the following user information and query, provide a concise health advice: %s");
        PROMPTS.put("SYMPTOM_ANALYSIS", "You are a medical professional. Analyze the following symptoms and provide a possible diagnosis: %s");
        PROMPTS.put("PATIENT_SUMMARY", "You are a healthcare provider. Summarize the following patient information and highlight key health aspects: %s");
        PROMPTS.put("MEDICATION_MANAGEMENT", "You are a pharmacist. Review the following medication list and provide advice on proper management and potential interactions: %s");
        PROMPTS.put("CHAT", "You are a friendly medical chatbot. Engage in a conversation about the following health-related topic: %s");
        PROMPTS.put("EMERGENCY_ASSIST", "You are an emergency response advisor. Please analyse whether the user is in a state of emergency based on the following data(if it is emergency, your response should contains ALERT EMERGENCY): %s");
        PROMPTS.put("TRANSLATION", "You are a medical translator. Translate the following health-related text, ensuring medical terminology is accurately conveyed: %s");
        PROMPTS.put("REGIONAL_REPORT", "You are a public health analyst. Based on the following regional health data, provide a concise report highlighting key trends and concerns: %s");
    }
    
    public static String getPrompt(String functionName, String userInfo) {
        String promptTemplate = PROMPTS.getOrDefault(functionName, DEFAULT_PROMPT);
        return String.format(promptTemplate, userInfo);
    }

    private static ChatLanguageModel createChatLanguageModel() {
        String apiKey = SecretsManagerUtil.getSecret();

        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(MODEL_NAME)
                .temperature(TEMPERATURE)
                .maxTokens(MAX_TOKENS)
                .build();
    }

    public static String getChatResponse(String functionName, String userInput) {
        ChatLanguageModel model = createChatLanguageModel();
        String prompt = getPrompt(functionName, userInput);
        return model.generate(prompt);
    }
}