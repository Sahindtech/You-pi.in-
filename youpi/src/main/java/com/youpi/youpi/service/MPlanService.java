package com.youpi.youpi.service;

import com.youpi.youpi.dto.MPlanOperatorResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MPlanService {

    @Value("${mplan.api.key}")
    private String apiKey;

    @Value("${mplan.api.operator_check_url}")
    private String operatorCheckUrl;

    @Value("${mplan.api.plans_url}")
    private String plansUrl;

    @Value("${mplan.api.recharge_url}")
    private String rechargeUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * PDF Page 12: Operator Check
     * http://operatorcheck.mplan.in/api/operatorinfo.php?apikey=[key]&tel=[mobile]
     */
    // 🔍 DEBUG MODE: Raw Response dekhne ke liye
    public MPlanOperatorResponseDTO checkOperator(String mobileNumber) {
        String url = UriComponentsBuilder.fromHttpUrl(operatorCheckUrl)
                .queryParam("apikey", apiKey)
                .queryParam("tel", mobileNumber)
                .toUriString();

        try {
            // Pehle response ko String (Text) ki tarah lo
            String rawResponse = restTemplate.getForObject(url, String.class);

            // Console me print karo ki API ne kya bheja
            System.out.println("🔴 RAW API RESPONSE: " + rawResponse);

            // Ab JSON convert karne ki koshish karo (ObjectMapper use karke)
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.readValue(rawResponse, MPlanOperatorResponseDTO.class);

        } catch (Exception e) {
            System.out.println("🔴 Error Parsing JSON: " + e.getMessage());
            throw new RuntimeException("Failed to check operator: " + e.getMessage());
        }
    }

    /**
     * PDF Page 1: R-Offer (Recharge Offer)
     * https://www.mplan.in/api/plans.php?apikey=[key]&offer=roffer&tel=[mobile]&operator=[operator]
     */
    public String fetchROffer(String mobileNumber, String operator) {
        String url = UriComponentsBuilder.fromHttpUrl(plansUrl)
                .queryParam("apikey", apiKey)
                .queryParam("offer", "roffer")
                .queryParam("tel", mobileNumber)
                .queryParam("operator", operator) // Must be: Airtel, BSNL, Jio, Vodafone, Idea
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

    /**
     * PDF Page 1: Simple Plan
     */
    public String fetchSimplePlans(String circle, String operator) {
        String url = UriComponentsBuilder.fromHttpUrl(plansUrl)
                .queryParam("apikey", apiKey)
                .queryParam("cricle", circle) // Note: PDF says "cricle" not "circle"
                .queryParam("operator", operator)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }

    /**
     * ACTUAL RECHARGE (Placeholder)
     * The PDF did not have this URL, but this is how you will trigger it.
     */
    public String performRecharge(String mobileNumber, String operator, String amount, String uniqueOrderId) {
        // Construct the standard URL structure. Verify parameters with your senior.
        String url = UriComponentsBuilder.fromHttpUrl(rechargeUrl)
                .queryParam("apikey", apiKey)
                .queryParam("number", mobileNumber)
                .queryParam("operator", operator)
                .queryParam("amount", amount)
                .queryParam("orderid", uniqueOrderId)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}