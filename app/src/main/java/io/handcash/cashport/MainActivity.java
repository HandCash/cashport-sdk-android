package io.handcash.cashport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;

import io.handcash.cashport.sdk.entity.AuthorizationConfiguration;
import io.handcash.cashport.sdk.entity.AuthorizationRequest;
import io.handcash.cashport.sdk.entity.AuthorizationResponse;
import io.handcash.cashport.sdk.entity.CashPortApiError;
import io.handcash.cashport.sdk.entity.PersonalInfoPermission;
import io.handcash.cashport.sdk.entity.SignTransactionRequest;

public class MainActivity extends AppCompatActivity {

    private static final long TIP_SATOSHI_VALUE = 2500L;
    private static final String EAT_BCH_ADDRESS = "bitcoincash:pp8skudq3x5hzw8ew7vzsw8tn4k8wxsqsv0lt0mf3g";

    private String userHandle;
    private String authToken;
    private String channelId;

    private Button btnUseMyCashPort;
    private Button btnTipToHandle;
    private Button btnTipToMyself;
    private Button btnDonate;

    private TextView tvLoginTitle;
    private EditText editHandle;
    private View tvContainerSend;

    private CashPort cashPort;
    private DemoAppCache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        initCashPort();
        setupViews();
        onNewIntent( getIntent() );
        loadCredentials();
    }

    @Override
    public void finish() {
        super.finish();
        cashPort.onFinish();
    }

    private void initCashPort() {
        cashPort = CashPort.with( this );
    }

    private void loadCredentials() {
        cache = new DemoAppCache( this );
        long expirationTimestamp = cache.getTokenExpirationTimestamp();
        long currentTimestamp = System.currentTimeMillis() / 1000;
        cache.printCache();
        if ( currentTimestamp < expirationTimestamp ) {
            userHandle = cache.getHandle();
            authToken = cache.getAuthorizationToken();
            channelId = cache.getChannelId();
            onUserConnected();
        }
    }

    private void storeCredentials(AuthorizationResponse authorizationResponse) {
        cache.setAuthorizationToken( authorizationResponse.getAuthorizationToken() );
        cache.setHandle( authorizationResponse.getUserHandle() );
        cache.setChannelId( authorizationResponse.getChannelId() );
        cache.setTokenExpirationTimestamp( authorizationResponse.getExpirationTimestamp() );
        cache.printCache();
    }

    private void setupViews() {
        setContentView( R.layout.activity_main );

        btnDonate = findViewById( R.id.btn_donate );
        btnDonate.setOnClickListener( v -> onClickDonateToEatBch() );

        btnTipToMyself = findViewById( R.id.btn_send_myself );
        btnTipToMyself.setOnClickListener( v -> onClickTipToMyself() );

        btnUseMyCashPort = findViewById( R.id.btn_use_cashport );
        btnUseMyCashPort.setOnClickListener( v -> onClickUseCashPort() );

        btnTipToHandle = findViewById( R.id.btn_send );
        btnTipToHandle.setOnClickListener( v -> onClickTipToHandle() );

        btnUseMyCashPort = findViewById( R.id.btn_use_cashport );
        tvLoginTitle = findViewById( R.id.tv_login_title );
        editHandle = findViewById( R.id.edit_handle );
        tvContainerSend = findViewById( R.id.container_send );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent( intent );
        AuthorizationConfiguration config = AuthorizationConfigurationBuilder.create()
                .setScheme( "app" )
                .setHost( "handle-tippr.io" )
                .setSuccessPath( "/cashport/onSuccess" )
                .setDeniedPath( "/cashport/onDeny" )
                .build();
        cashPort.onNewIntent( intent, config, new IRequestAuthorizationCallback() {
            @Override
            public void onGrantAuthorization(AuthorizationResponse authorizationResponse) {
                storeCredentials( authorizationResponse );
                loadCredentials();
                onUserConnected();
            }

            @Override
            public void onDenyAuthorization() {
                Toast.makeText( getApplicationContext(), "We need permissions!", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onInternalError(Throwable throwable) {
                throwable.printStackTrace();
                showMessage( "Something went wrong :(" );
            }
        } );
    }

    private void onClickUseCashPort() {
        AuthorizationRequest authorizationRequest = AuthorizationRequestBuilder.withAppId( ApiParameters.API_ID )
                .addPermission( PersonalInfoPermission.HANDLE )
                .build();
        try {
            cashPort.requestAuthorization( authorizationRequest );
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
            showMessage( "Something went wrong :(" );
        }
    }

    private void onClickTipToHandle() {
        hideKeyboard();
        String handle = editHandle.getText().toString();

        SignTransactionRequest request = SignTransactionRequestBuilder.useApiId( ApiParameters.API_ID )
                .withCredentials( userHandle, authToken, channelId )
                .addPayment( PaymentRequestFactory.create().getPayToHandle( handle, TIP_SATOSHI_VALUE ) )
                .build();
        sendSignTransactionRequest( request, btnTipToHandle );
    }

    private void onClickTipToMyself() {
        SignTransactionRequest request = SignTransactionRequestBuilder.useApiId( ApiParameters.API_ID )
                .withCredentials( userHandle, authToken, channelId )
                .addPayment( PaymentRequestFactory.create().getPayToHandle( userHandle, TIP_SATOSHI_VALUE ) )
                .build();
        sendSignTransactionRequest( request, btnTipToMyself );
    }

    private void onClickDonateToEatBch() {
        SignTransactionRequest request = SignTransactionRequestBuilder.useApiId( ApiParameters.API_ID )
                .withCredentials( userHandle, authToken, channelId )
                .addPayment( PaymentRequestFactory.create().getPayToAddress( EAT_BCH_ADDRESS, TIP_SATOSHI_VALUE ) )
                .build();
        sendSignTransactionRequest( request, btnDonate );
    }

    private void sendSignTransactionRequest(SignTransactionRequest request, Button button) {
        String originText = button.getText().toString();
        cashPort.sendSignTransactionRequest( request, new ISignTransactionRequestCallback() {
            @Override
            public void onStart() {
                button.setEnabled( false );
                button.setText( "Loading..." );
            }

            @Override
            public void onEnd() {
                button.setText( originText );
                button.setEnabled( true );
            }

            @Override
            public void onSuccess() {
                editHandle.getText().clear();
                showMessage( "Sent!" );
            }

            @Override
            public void onTokenExpired() {
                showMessage( "Token expired!" );
            }

            @Override
            public void onAuthorizedFundsLimitReached() {
                showMessage( "Authorized funds limit reached" );
            }

            @Override
            public void onInsufficientWalletFunds() {
                showMessage( "Insufficient wallet funds" );
            }

            @Override
            public void onNotAuthorized() {
                showMessage( "Not authorized" );
            }

            @Override
            public void onDeviceNotAvailable() {
                showMessage( "Device not available. Check internet connection?" );
            }

            @Override
            public void onInternalError() {
                showMessage( "Internal error" );
            }

            @Override
            public void onBadRequest(String message, CashPortApiError error) {
                showMessage( "Error sending request: " + message );
            }

            @Override
            public void onAPICallError(Throwable throwable) {
                throwable.printStackTrace();
                showMessage( "Error sending request" );
            }
        } );
    }

    private void onUserConnected() {
        tvLoginTitle.setText( Html.fromHtml( "Connected as <b>@" + userHandle + "</b>" ) );
        tvContainerSend.setVisibility( View.VISIBLE );
        btnUseMyCashPort.setVisibility( View.GONE );
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService( Activity.INPUT_METHOD_SERVICE );
        View view = getCurrentFocus();
        if ( view == null ) {
            view = new View( this );
        }
        imm.hideSoftInputFromWindow( view.getWindowToken(), 0 );
    }

    private void showMessage(String message) {
        Toast.makeText( getApplicationContext(), message, Toast.LENGTH_SHORT ).show();
    }

}
