package eu.darken.androidjavastarter.main.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import eu.darken.mvpbakery.injection.broadcastreceiver.HasManualBroadcastReceiverInjector;
import timber.log.Timber;

public class ExampleReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Timber.v("onReceive(%s, %s)", context, intent);
        ((HasManualBroadcastReceiverInjector) context.getApplicationContext()).broadcastReceiverInjector().inject(this);
    }
}
