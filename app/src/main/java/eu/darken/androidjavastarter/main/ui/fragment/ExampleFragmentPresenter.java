package eu.darken.androidjavastarter.main.ui.fragment;

import android.support.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import eu.darken.androidjavastarter.main.core.SomeRepo;
import eu.darken.mvpbakery.base.Presenter;
import eu.darken.mvpbakery.injection.ComponentPresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

@ExampleFragmentComponent.Scope
public class ExampleFragmentPresenter extends ComponentPresenter<ExampleFragmentPresenter.View, ExampleFragmentComponent> {

    private final SomeRepo someRepo;
    private Disposable emojiSub = Disposables.disposed();

    @Inject
    ExampleFragmentPresenter(SomeRepo someRepo) {
        this.someRepo = someRepo;
    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        subToEmojis();
    }

    private void subToEmojis() {
        if (getView() != null && emojiSub.isDisposed()) {
            emojiSub = someRepo.getEmoji()
                    .repeatWhen(errors -> errors.flatMapSingle(error -> Single.timer(1, TimeUnit.SECONDS)))
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(emoji -> onView(v -> v.showEmoji(emoji)));
        } else {
            emojiSub.dispose();
        }
    }

    void onGetNewEmoji() {
        emojiSub.dispose();
        subToEmojis();
    }

    interface View extends Presenter.View {
        void showEmoji(String emoji);
    }
}
