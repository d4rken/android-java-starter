package eu.darken.androidjavastarter;

import android.app.Activity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Component;
import dagger.MembersInjector;
import eu.darken.mvpbakery.injection.ComponentSource;


@AppComponent.Scope
@Component(modules = {
        AndroidModule.class,
        ActivityBinderModule.class,
        ServiceBinderModule.class,
        ReceiverBinderModule.class
})
public interface AppComponent extends MembersInjector<App> {
    void inject(App app);

    ComponentSource<Activity> activityInjector();

    @Component.Builder
    interface Builder {
        Builder androidModule(AndroidModule module);

        AppComponent build();
    }

    @Documented
    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Scope {
    }
}
