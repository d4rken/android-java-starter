package eu.darken.androidjavastarter.main.core.service;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.androidjavastarter.App;
import eu.darken.androidjavastarter.common.fragments.SmartService;

public class ExampleService extends SmartService {

    @Inject ExampleBinder binder;

    @Override
    public void onCreate() {
        ((App) getApplication()).serviceInjector().inject(this);
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}