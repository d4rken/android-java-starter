package eu.darken.androidjavastarter.main.ui;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.androidjavastarter.main.core.SomeRepo;
import eu.darken.mvpbakery.base.Presenter;
import eu.darken.mvpbakery.injection.ComponentPresenter;

public class MainActivityPresenter extends ComponentPresenter<MainActivityPresenter.View, MainActivityComponent> {
    private final SomeRepo someRepo;

    @Inject
    public MainActivityPresenter(SomeRepo someRepo) {
        this.someRepo = someRepo;
    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        onView(v -> {
            if (someRepo.isShowFragment()) {
                v.showExampleFragment();
            }
        });
    }

    interface View extends Presenter.View {

        void showExampleFragment();
    }
}
