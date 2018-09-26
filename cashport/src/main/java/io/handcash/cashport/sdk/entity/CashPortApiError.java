package io.handcash.cashport.sdk.entity;

public enum CashPortApiError {
    UNKNOWN_APP_ID( -100 ),
    UNKNOWN_HANDLE( -101 ),
    INVALID_API_CREDENTIALS( -200 ),
    INVALID_PAYMENT( -202 ),
    INVALID_REQUEST( -203 ),
    UNKNOWN_ERROR( -999 );

    private int errorCode;

    CashPortApiError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static CashPortApiError getFromCode(int code) {
        for ( CashPortApiError error : CashPortApiError.values() ) {
            if ( error.errorCode == code ) {
                return error;
            }
        }
        throw new IllegalArgumentException( "Unknown error code: " + code );
    }
}
