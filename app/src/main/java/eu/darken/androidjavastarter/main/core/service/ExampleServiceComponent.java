package eu.darken.androidjavastarter.main.core.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Subcomponent;
import eu.darken.mvpbakery.injection.service.ServiceComponent;


@ExampleServiceComponent.Scope
@Subcomponent()
public interface ExampleServiceComponent extends ServiceComponent<ExampleService> {

    @Subcomponent.Builder
    abstract class Builder extends ServiceComponent.Builder<ExampleService, ExampleServiceComponent> {

    }

    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Scope {}
}
