package eu.darken.androidjavastarter;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;

import javax.inject.Inject;

import eu.darken.mvpbakery.injection.ComponentSource;
import eu.darken.mvpbakery.injection.ManualInjector;
import eu.darken.mvpbakery.injection.activity.HasManualActivityInjector;
import eu.darken.mvpbakery.injection.broadcastreceiver.HasManualBroadcastReceiverInjector;
import eu.darken.mvpbakery.injection.service.HasManualServiceInjector;
import timber.log.Timber;


public class App extends Application implements HasManualActivityInjector, HasManualBroadcastReceiverInjector, HasManualServiceInjector {
    static final String TAG = App.logTag("ExampleApp");

    @Inject AppComponent appComponent;
    ManualInjector<Activity> activityInjector;
    @Inject ComponentSource<BroadcastReceiver> receiverInjector;
    @Inject ComponentSource<Service> serviceInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
        DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build()
                .injectMembers(this);

        activityInjector = appComponent.activityInjector();

        Timber.tag(TAG).d("onCreate() done!");
    }

    @Override
    public ManualInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Override
    public ManualInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return receiverInjector;
    }

    @Override
    public ManualInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    public static String logTag(String... tags) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tags.length; i++) {
            sb.append(tags[i]);
            if (i < tags.length - 1) sb.append(":");
        }
        return sb.toString();
    }
}
