package io.handcash.cashport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.freaks.app.deeplink.DeepLinkHandler;
import com.freaks.app.deeplink.IDeepLinkLauncherFactory;
import com.freaks.app.deeplink.URIBuilder;
import com.freaks.app.deeplink.util.StringUtils;
import com.google.firebase.messaging.FirebaseMessaging;

import java.net.MalformedURLException;

import io.handcash.cashport.sdk.ServiceFactory;
import io.handcash.cashport.sdk.api_callback.SendSignTransactionAPIRequestCallback;
import io.handcash.cashport.sdk.deeplink.CashPortDeepLinkLauncherFactory;
import io.handcash.cashport.sdk.deeplink.ICashPortDeepLinkListener;
import io.handcash.cashport.sdk.deeplink.QueryParameters;
import io.handcash.cashport.sdk.entity.AuthorizationConfiguration;
import io.handcash.cashport.sdk.entity.AuthorizationRequest;
import io.handcash.cashport.sdk.entity.AuthorizationResponse;
import io.handcash.cashport.sdk.entity.SignTransactionRequest;
import io.handcash.cashport.sdk.firebase.FirebaseChannelHandler;
import io.handcash.cashport.sdk.firebase.FirebaseInitializer;

public class CashPort {

    private Context context;
    private FirebaseChannelHandler channelHandler;

    public static CashPort with(Activity activity) {
        FirebaseInitializer.create().initializeFirebase( activity );
        return new CashPort( activity );
    }

    protected CashPort(Context context) {
        this.context = context;
        this.channelHandler = new FirebaseChannelHandler( context );
    }

    public void requestAuthorization(AuthorizationRequest authorizationRequest) throws MalformedURLException {
        String uri = URIBuilder.fromURI( BuildConfig.CASHPORT_AUTHORIZATION_REQUEST_URI )
                .addQueryParameter( QueryParameters.APP_ID, authorizationRequest.getAppId() )
                .addQueryParameter( QueryParameters.PERSONAL_INFO, StringUtils.join( ",", authorizationRequest.getPermissionsAsQueryNames() ) )
                .buildAsString();
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( uri ) );
        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
        context.startActivity( intent );
    }

    public void onNewIntent(Intent intent, AuthorizationConfiguration configuration, IRequestAuthorizationCallback callback) {
        if ( intent != null && intent.getData() != null ) {
            IDeepLinkLauncherFactory factory = new CashPortDeepLinkLauncherFactory( configuration.getSuccessPath(),
                    configuration.getDeniedPath(), new ICashPortDeepLinkListener() {
                @Override
                public void onSuccess(AuthorizationResponse authorizationResponse) {
                    FirebaseMessaging.getInstance().subscribeToTopic( authorizationResponse.getChannelId() );
                    callback.onGrantAuthorization( authorizationResponse );
                }

                @Override
                public void onDeny() {
                    callback.onDenyAuthorization();
                }
            } );
            DeepLinkHandler deepLinkHandler = DeepLinkHandler.createWithDefaultParser( factory,
                    configuration.getSchema(), configuration.getHost() );
            deepLinkHandler.handleLink( intent.getData().toString() );
        }
    }

    public void sendSignTransactionRequest(SignTransactionRequest request,
                                           ISignTransactionRequestCallback sendSignTransactionRequestCallback) {
        channelHandler.updateChannel( request.getChannelId() );
        channelHandler.registerTransactionRequestCallback( sendSignTransactionRequestCallback );
        sendSignTransactionRequestCallback.onStart();
        ServiceFactory.create()
                .getCashPortService()
                .sendSignRequest( request )
                .enqueue( new SendSignTransactionAPIRequestCallback( sendSignTransactionRequestCallback ) );
    }

    public void onFinish() {
        context = null;
        channelHandler.closeChannel();
        channelHandler = null;
    }

}
