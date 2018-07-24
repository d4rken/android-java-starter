package eu.darken.androidjavastarter.main.core;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import eu.darken.androidjavastarter.main.ui.MainActivityComponent;
import io.reactivex.Single;


@MainActivityComponent.Scope
public class SomeRepo {
    static final List<String> EMOJIS = Arrays.asList("\uD83D\uDE00", "\uD83D\uDE02", "\uD83E\uDD17", "\uD83D\uDE32");

    @Inject
    public SomeRepo() {
    }

    public boolean isShowFragment() {
        return true;
    }

    public Single<String> getEmoji() {
        return Single.create(emitter -> emitter.onSuccess(EMOJIS.get((int) (Math.random() * EMOJIS.size()))));
    }
}
