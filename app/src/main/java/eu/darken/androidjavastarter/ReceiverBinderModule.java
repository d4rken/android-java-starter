package eu.darken.androidjavastarter;

import android.content.BroadcastReceiver;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.BroadcastReceiverKey;
import dagger.multibindings.IntoMap;
import eu.darken.androidjavastarter.main.core.receiver.ExampleReceiver;
import eu.darken.androidjavastarter.main.core.receiver.ExampleReceiverComponent;

@Module(subcomponents = {
        ExampleReceiverComponent.class
})
abstract class ReceiverBinderModule {

    @Binds
    @IntoMap
    @BroadcastReceiverKey(ExampleReceiver.class)
    abstract AndroidInjector.Factory<? extends BroadcastReceiver> exampleReceiver(ExampleReceiverComponent.Builder impl);

}