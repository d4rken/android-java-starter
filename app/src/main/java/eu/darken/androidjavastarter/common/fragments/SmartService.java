package eu.darken.androidjavastarter.common.fragments;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;

import eu.darken.androidjavastarter.App;
import timber.log.Timber;


public abstract class SmartService extends Service {
    private final String tag;

    {
        tag = App.logTag("Service", this.getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")");
    }

    @Override
    public void onCreate() {
        Timber.tag(tag).d("onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Timber.tag(tag).d("onStart(intent=%s, startId=%d)", intent, startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.tag(tag).d("onStartCommand(intent=%s, flags=%s startId=%d)", intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Timber.tag(tag).d("onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Timber.tag(tag).d("onConfigurationChanged(newConfig=%s)", newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        Timber.tag(tag).d("onLowMemory()");
        super.onLowMemory();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Timber.tag(tag).d("onUnbind(intent=%s)", intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Timber.tag(tag).d("onRebind(intent=%s)", intent);
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Timber.tag(tag).d("onTaskRemoved(rootIntent=%s)", rootIntent);
        super.onTaskRemoved(rootIntent);
    }
}
