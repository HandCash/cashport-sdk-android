package io.handcash.cashport.sdk.entity;

public class TransactionOutputRequest {

    private String scriptPubKeyHex;
    private long satoshiValue;

    public TransactionOutputRequest() {
    }

    public TransactionOutputRequest(String scriptPubKeyHex, long satoshiValue) {
        this.scriptPubKeyHex = scriptPubKeyHex;
        this.satoshiValue = satoshiValue;
    }

    public String getScriptPubKeyHex() {
        return scriptPubKeyHex;
    }

    public void setScriptPubKeyHex(String scriptPubKeyHex) {
        this.scriptPubKeyHex = scriptPubKeyHex;
    }

    public long getSatoshiValue() {
        return satoshiValue;
    }

    public void setSatoshiValue(long satoshiValue) {
        this.satoshiValue = satoshiValue;
    }

    @Override
    public String toString() {
        return "Output{" +
                "scriptPubKeyHex='" + scriptPubKeyHex + '\'' +
                ", satoshiValue=" + satoshiValue +
                '}';
    }
}
