package io.handcash.cashport.sdk.entity;

public enum SignTransactionResponseCode {
    ACCEPTED( 200 ),

    NOT_AUTHORIZED( 401 ),
    LIMIT_REACHED( 402 ),
    TOKEN_EXPIRED( 403 ),

    INTERNAL_ERROR( 500 ),
    INSUFFICIENT_FUNDS( 501 );

    private int statusCode;

    SignTransactionResponseCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static SignTransactionResponseCode getFromStatusCode(int statusCode) {
        for ( SignTransactionResponseCode code : SignTransactionResponseCode.values() ) {
            if ( code.statusCode == statusCode ) {
                return code;
            }
        }
        throw new IllegalArgumentException( "Unknown statusCode " + statusCode );
    }


}
