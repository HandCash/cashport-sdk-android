package io.handcash.cashport.sdk.entity;

public class PaymentRequest {

    private PaymentRequestType type;
    private String to;
    private long satoshiValue;

    public PaymentRequest() {
    }

    public PaymentRequest(PaymentRequestType type, String to, long satoshiValue) {
        this.type = type;
        this.to = to;
        this.satoshiValue = satoshiValue;
    }

    public PaymentRequestType getType() {
        return type;
    }

    public void setType(PaymentRequestType type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getSatoshiValue() {
        return satoshiValue;
    }

    public void setSatoshiValue(long satoshiValue) {
        this.satoshiValue = satoshiValue;
    }

    @Override
    public String toString() {
        return "PaymentTransactionRequest{" +
                "type=" + type +
                ", to='" + to + '\'' +
                ", satoshiValue=" + satoshiValue +
                '}';
    }

}
