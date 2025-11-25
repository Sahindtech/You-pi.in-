package com.youpi.youpi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

// ✅ Yeh line add kari hai taaki extra fields (jaise 'records') aane par crash na ho
@JsonIgnoreProperties(ignoreUnknown = true)
public class MPlanOperatorResponseDTO {

    // --- Success Fields (Jab plan active hoga tab ye aayenge) ---
    @JsonProperty("Tel")
    private String mobile;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("circle")
    private String circle;

    // --- Error/Status Fields (Jo abhi aa rahe hain) ---
    @JsonProperty("status")
    private Integer status;

    @JsonProperty("records")
    private Map<String, String> records; // Error message yahan aayega

    // --- Getters and Setters ---
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getCircle() { return circle; }
    public void setCircle(String circle) { this.circle = circle; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public Map<String, String> getRecords() { return records; }
    public void setRecords(Map<String, String> records) { this.records = records; }
}