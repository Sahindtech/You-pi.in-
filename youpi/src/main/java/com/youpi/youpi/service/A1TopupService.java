package com.youpi.youpi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class A1TopupService {

    @Value("${a1topup.api.url}")
    private String apiUrl;

    @Value("${a1topup.api.username}")
    private String username;

    @Value("${a1topup.api.password}")
    private String password;

    private final RestTemplate restTemplate = new RestTemplate();

    public String performRecharge(String mobile, String amount, String operatorCode, String circleCode, String orderId) {

        // Construct the URL
        // https://business.a1topup.com/recharge/api?username=...&pwd=...&format=json
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("username", username)
                .queryParam("pwd", password)
                .queryParam("circlecode", circleCode)
                .queryParam("operatorcode", operatorCode)
                .queryParam("number", mobile)
                .queryParam("amount", amount)
                .queryParam("orderid", orderId)
                .queryParam("format", "json") // We want JSON response
                .toUriString();

        try {
            System.out.println("🚀 Calling A1Topup API: " + url);

            // Call the API
            String response = restTemplate.getForObject(url, String.class);

            System.out.println("✅ A1Topup Response: " + response);
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"FAILED\", \"message\":\"" + e.getMessage() + "\"}";
        }
    }
}