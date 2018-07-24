package eu.darken.androidjavastarter.main.core.receiver;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Subcomponent;
import eu.darken.mvpbakery.injection.broadcastreceiver.BroadcastReceiverComponent;

@ExampleReceiverComponent.Scope
@Subcomponent()
public interface ExampleReceiverComponent extends BroadcastReceiverComponent<ExampleReceiver> {

    @Subcomponent.Builder
    abstract class Builder extends BroadcastReceiverComponent.Builder<ExampleReceiver, ExampleReceiverComponent> {

    }

    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Scope {
    }
}
