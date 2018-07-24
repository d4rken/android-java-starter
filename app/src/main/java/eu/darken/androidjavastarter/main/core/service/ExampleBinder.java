package eu.darken.androidjavastarter.main.core.service;

import android.os.Binder;

import javax.inject.Inject;

@ExampleServiceComponent.Scope
class ExampleBinder extends Binder {
    @Inject
    public ExampleBinder(ExampleService service) {
    }
}
