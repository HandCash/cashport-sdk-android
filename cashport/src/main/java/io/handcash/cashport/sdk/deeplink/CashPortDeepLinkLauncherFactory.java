package io.handcash.cashport.sdk.deeplink;

import com.freaks.app.deeplink.DeepLink;
import com.freaks.app.deeplink.IDeepLinkLauncher;
import com.freaks.app.deeplink.IDeepLinkLauncherFactory;

import io.handcash.cashport.sdk.entity.AuthorizationResponse;

public class CashPortDeepLinkLauncherFactory implements IDeepLinkLauncherFactory {

    private String successAction;
    private String deniedAction;
    private ICashPortDeepLinkListener deepLinkListener;

    public CashPortDeepLinkLauncherFactory(String successAction, String deniedAction, ICashPortDeepLinkListener deepLinkListener) {
        this.successAction = successAction;
        this.deniedAction = deniedAction;
        this.deepLinkListener = deepLinkListener;
    }

    @Override
    public IDeepLinkLauncher getDeepLinkLauncher(DeepLink deepLink) {
        if ( deepLink.getAction().equals( successAction ) ) {
            return new AuthorizationGrantedDeepLinkLauncher() {
                @Override
                public void onLaunch(AuthorizationResponse authorizationResponse) {
                    deepLinkListener.onSuccess( authorizationResponse );
                }
            };
        } else if ( deepLink.getAction().equals( deniedAction ) ) {
            return deepLink1 -> deepLinkListener.onDeny();
        }
        return null;
    }

}
