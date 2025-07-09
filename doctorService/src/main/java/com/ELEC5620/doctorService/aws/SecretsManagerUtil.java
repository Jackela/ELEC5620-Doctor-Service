package com.ELEC5620.doctorService.aws;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecretsManagerUtil {

    private static final String SECRET_NAME = "ELEC5620/MedConnect/AIAgentService";
    private static final String REGION = "ap-southeast-2"; 

    public static String getSecret() {
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(REGION)
                .build();

        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(SECRET_NAME);

        try {
            GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
            String secret = getSecretValueResult.getSecretString();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(secret);
            if (jsonNode.has("OPENAI_API_KEY")) {
                return jsonNode.get("OPENAI_API_KEY").asText();
            } else {
                throw new RuntimeException("OPENAI_API_KEY not found in the secret");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving secret from Secrets Manager", e);
        }
    }
}