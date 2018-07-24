package eu.darken.androidjavastarter;

import android.app.Service;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ServiceKey;
import dagger.multibindings.IntoMap;
import eu.darken.androidjavastarter.main.core.service.ExampleService;
import eu.darken.androidjavastarter.main.core.service.ExampleServiceComponent;

@Module(subcomponents = {ExampleServiceComponent.class})
abstract class ServiceBinderModule {

    @Binds
    @IntoMap
    @ServiceKey(ExampleService.class)
    abstract AndroidInjector.Factory<? extends Service> exampleService(ExampleServiceComponent.Builder impl);
}