package io.handcash.cashport.sdk.entity;

import java.util.List;

public class SignTransactionRequest {

    private String apiId;
    private String authToken;
    private String channelId;
    private String handle;
    private List<PaymentRequest> payments;

    public SignTransactionRequest() {
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<PaymentRequest> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentRequest> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "SignTransactionRequest{" +
                "apiId='" + apiId + '\'' +
                ", authToken='" + authToken + '\'' +
                ", channelId='" + channelId + '\'' +
                ", handle='" + handle + '\'' +
                ", payments=" + payments +
                '}';
    }

}
