package com.ELEC5620.doctorService.tools;

import dev.langchain4j.agent.tool.Tool;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;

@Component
public class ApiTools {

    private final RestTemplate restTemplate;

    public ApiTools(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Tool(name = "translate", value = "Translate text into a specified language")
    public String callTranslateApi(String text, String sourceLanguage, String targetLanguage) {
        String url = "https://yhrj2rgi0c.execute-api.ap-southeast-2.amazonaws.com/prod/translate";

        Map<String, String> requestBody = Map.of("text", text, "sourceLanguage", sourceLanguage, "targetLanguage", targetLanguage);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return response.getBody();
    }

    @Tool(name = "healthAdvice", value = "Get health advice based on symptoms")
    public String callHealthAdviceApi(String userInput) {
        String url = "https://yhrj2rgi0c.execute-api.ap-southeast-2.amazonaws.com/prod/health-advice";

        Map<String, String> requestBody = Map.of("userInput", userInput);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return response.getBody();
    }

    
    @Tool(name = "patientSummary", value = "Get patient summary based on userId")
    public String callPatientSummaryApi(String userId) {
        String url = "https://yhrj2rgi0c.execute-api.ap-southeast-2.amazonaws.com/prod/patient-summary/" + userId;

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        return response.getBody();
    }
    @Tool(name = "regionalReport", value = "Get regional report based on userId")
    public String callRegionalReportApi() {
        String url = "https://yhrj2rgi0c.execute-api.ap-southeast-2.amazonaws.com/prod/regional-report/";

        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        return response.getBody();
    }
    
}
