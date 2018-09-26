package io.handcash.cashport;

import io.handcash.cashport.sdk.entity.PaymentRequest;
import io.handcash.cashport.sdk.entity.PaymentRequestType;

public class PaymentRequestFactory {

    public static PaymentRequestFactory create() {
        return new PaymentRequestFactory();
    }

    public PaymentRequest getPayToHandle(String handle, long satoshiValue) {
        return new PaymentRequest( PaymentRequestType.PayToHandle, handle, satoshiValue );
    }

    public PaymentRequest getPayToAddress(String address, long satoshiValue) {
        return new PaymentRequest( PaymentRequestType.PayToAddress, address, satoshiValue );
    }

}
