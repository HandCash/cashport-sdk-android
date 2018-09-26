package io.handcash.cashport.sdk;


import io.handcash.cashport.sdk.api.ICashPortService;

public class ServiceFactory {

    public static ServiceFactory create() {
        return new ServiceFactory();
    }

    public ICashPortService getCashPortService() {
        return ComponentsFactory.create()
                .getCashPortRetrofit()
                .create( ICashPortService.class );
    }
}
