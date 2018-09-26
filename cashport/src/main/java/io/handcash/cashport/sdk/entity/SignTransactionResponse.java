package io.handcash.cashport.sdk.entity;

import java.io.Serializable;
import java.util.Map;

public class SignTransactionResponse implements Serializable {

    private SignTransactionResponseCode statusCode;
    private String developerMessage;
    private Map<String, String> parameters;

    public SignTransactionResponse() {
    }

    public SignTransactionResponseCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(SignTransactionResponseCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "SignTransactionResponse{" +
                "statusCode=" + statusCode +
                ", developerMessage='" + developerMessage + '\'' +
                ", parameters=" + parameters +
                '}';
    }

}