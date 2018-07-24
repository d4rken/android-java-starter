package eu.darken.androidjavastarter;

import android.app.Activity;

import eu.darken.mvpbakery.injection.ManualInjector;

public class ExampleApplicationMock extends App {

    public void setActivityComponentSource(ManualInjector<Activity> injector) {
        this.activityInjector = injector;
    }
}
