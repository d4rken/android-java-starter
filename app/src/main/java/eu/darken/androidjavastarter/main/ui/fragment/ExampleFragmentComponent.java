package eu.darken.androidjavastarter.main.ui.fragment;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.Subcomponent;
import eu.darken.mvpbakery.injection.PresenterComponent;
import eu.darken.mvpbakery.injection.fragment.FragmentComponent;


@ExampleFragmentComponent.Scope
@Subcomponent()
public interface ExampleFragmentComponent extends PresenterComponent<ExampleFragmentPresenter.View, ExampleFragmentPresenter>, FragmentComponent<ExampleFragment> {
    @Subcomponent.Builder
    abstract class Builder extends FragmentComponent.Builder<ExampleFragment, ExampleFragmentComponent> {

    }

    @Documented
    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Scope {}
}
