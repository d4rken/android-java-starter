package eu.darken.androidjavastarter.main.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.darken.androidjavastarter.R;
import eu.darken.androidjavastarter.common.Check;
import eu.darken.androidjavastarter.common.fragments.SmartFragment;
import eu.darken.mvpbakery.base.MVPBakery;
import eu.darken.mvpbakery.base.ViewModelRetainer;
import eu.darken.mvpbakery.injection.InjectedPresenter;
import eu.darken.mvpbakery.injection.PresenterInjectionCallback;


public class ExampleFragment extends SmartFragment implements ExampleFragmentPresenter.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.emoji_text) TextView emojiText;

    @Inject ExampleFragmentPresenter presenter;

    public static Fragment newInstance() {
        return new ExampleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.example_fragment, container, false);
        addUnbinder(ButterKnife.bind(this, layout));
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        MVPBakery.<ExampleFragmentPresenter.View, ExampleFragmentPresenter>builder()
                .presenterFactory(new InjectedPresenter<>(this))
                .presenterRetainer(new ViewModelRetainer<>(this))
                .addPresenterCallback(new PresenterInjectionCallback<>(this))
                .attach(this);

        super.onActivityCreated(savedInstanceState);

        toolbar.setTitle(R.string.app_name);
        toolbar.setSubtitle(null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                Snackbar.make(Check.notNull(getView()), R.string.app_name, Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab)
    public void onFabClicked(View fab) {
        presenter.onGetNewEmoji();
    }

    @Override
    public void showEmoji(String emoji) {
        emojiText.setText(emoji);
    }
}
