# Cashport SDK - Android
An Android SDK to integrate CashPort in your apps: https://cashport.io

# Installing
**Step 1.** Add the JitPack repository to your build file.

Add it in your root *build.gradle* at the end of repositories:

```gradle
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
```

**Step 2.** Add the dependency.

```gradle
dependencies {
   implementation 'com.github.HandCash:cashport-sdk-android:v0.0.2'
}
```

# Getting Started
This section contains all the steps required to integrate *Cashport* in your app.

## 1. Get the API Credentials.

Go to https://cashport.io/developers and apply for the *API Credentials*. 

In the meanwhile you can use this `API_ID` to start developing.
 
`L77MZzEO72ZZSrRg58ysiGvveqFe51rK9lMDXKILD6YJf4lNibacSUx0xr979duv`

> Remember this are just temporal credentials.

## 2. Configure your manifest.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest ...>

    <application ...>
        <activity android:name=".ActivityWhereTheUserIsRedirectedAfterAuthorizeYourApp"
                  android:launchMode="singleTask">
            <intent-filter>
                <action android:name="android.intent.action.VIEW"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <category android:name="android.intent.category.BROWSABLE"/>
                <data android:scheme="your.app.scheme"
		      android:host="your.app.host" />
            </intent-filter>
        </activity>

    </application>

</manifest>
```

Pay attention to the following parameters.

### The redirection activity.

After the user authorizes your app to access to his *Cashport*, he will be redirected to the activity you specify in `android:name=".ActivityWhereTheUserIsRedirectedAfterAuthorizeYourApp"`

### The redirection URI.

URI's are defined as `scheme://host`. So you need to replace `your.app.host` and `your.app.scheme` by your respectives `scheme` and `host`.

For example, given the URI app://myFirstApp.cash you should get:

```xml
<intent-filter>
	<action android:name="android.intent.action.VIEW"/>
	<category android:name="android.intent.category.DEFAULT"/>
	<category android:name="android.intent.category.BROWSABLE"/>
	<data android:scheme="app" android:host="myFirstApp.cash"/>
</intent-filter>
```

✅ **Now your manifest is ready!**

## 3. Create an Authorization Request

When you want to launch the *Authorization Request*:

```java
private void onClickUseCashport() {
    AuthorizationRequest authorizationRequest = AuthorizationRequestBuilder.withAppId(ApiParameters.API_ID)
            .addPermission(PersonalInfoPermission.HANDLE)
            .build();
    try {
        CashPort.with( /* Your context */ ).requestAuthorization(authorizationRequest);
    } catch (MalformedURLException e) {
        e.printStackTrace();
        showMessage("Something went wrong :(");
    }
}
```
At this time, the user will be redirected to his *HandCash App*. Once he handles the request, will be redirected back to your app.

To handle the response add the following code inside your `Activity`:

```java
@Override
protected void onNewIntent(Intent intent) {
    super.onNewIntent( intent );
    AuthorizationConfiguration config = AuthorizationConfigurationBuilder.create()
            .setScheme( "your.app.scheme" )
            .setHost( "your.app.host" )
            .setSuccessPath( "/path/on/success" )
            .setDeniedPath( "/path/on/denied" )
            .build();
    cashPort.onNewIntent( intent, config, new IRequestAuthorizationCallback() {
        @Override
        public void onGrantAuthorization(AuthorizationResponse authorizationResponse) {
            // Great!
        }

        @Override
        public void onDenyAuthorization() {
            // Ouch
        }

        @Override
        public void onWalletInternalError(Throwable throwable) {
            // Ouch x2
        }
    } );
}
```

The values provided in `config` must match to your *API Credentials* to work properly. For example:
```java
AuthorizationConfigurationBuilder.create()
            .setScheme( "app" )
            .setHost( "handle-tippr.io" )
            .setSuccessPath( "/cashport/onSuccess" )
            .setDeniedPath( "/cashport/onDeny" )
            .build();
```

> Load from config file coming in next updates: `AuthorizationConfigurationBuilder.createFromFile("cashport-config.json")`


## 4. Create a Sign Transaction Request
Let's see how to tip to a $handle.

This is the code you need to create a *Sign Transaction Request* and handle the response. 

```java
private static final long TIP_SATOSHI_VALUE = 2500L;

private void tipToHandle(String handle){
    SignTransactionRequest request = SignTransactionRequestBuilder.useApiId( ApiParameters.API_ID )
            .withCredentials( userHandle, authToken, channelId ) // Got from the authorization
            .addPayment( PaymentRequestFactory.create().getPayToHandle( handle, TIP_SATOSHI_VALUE ) )
            .build();
    cashPort.sendSignTransactionRequest( request, new ISignTransactionRequestCallback() {
        @Override
        public void onStart() {
            // Show progress
        }

        @Override
        public void onEnd() {
            // Hide progress
        }

        @Override
        public void onSuccess(String transactionId) {
            // Yeah, you got it!
        }

        @Override
        public void onTokenExpired() {
            // Ouch, you need to refresh the token
        }

        @Override
        public void onAuthorizedFundsLimitReached() {
            // The funds limit set by the user to your app has been reached.
            // Now your need the user to authorize you again!
        }

        @Override
        public void onInsufficientWalletFunds() {
            // The user doesn't have enough funds
        }

        @Override
        public void onNotAuthorized() {
            // Your app is not authorized to perform this request
        }

        @Override
        public void onDeviceNotAvailable() {
            // User device not available. Check internet connection?
        }

        @Override
        public void onWalletInternalError() {
            // Ouch, our bad!
        }

        @Override
        public void onBadRequest(String message, CashPortApiError error) {
            // WTF are you doing?
        }

        @Override
        public void onAPICallError(Throwable throwable) {
            // Ouch, our bad!
        }
    } );
}
```

**✅ Congrats, you have completed your first authorized transaction!**

# License
MIT License

Copyright (c) 2018 HandCash

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
