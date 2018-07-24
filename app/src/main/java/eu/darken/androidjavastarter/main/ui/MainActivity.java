package eu.darken.androidjavastarter.main.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.ButterKnife;
import eu.darken.androidjavastarter.R;
import eu.darken.androidjavastarter.main.ui.fragment.ExampleFragment;
import eu.darken.mvpbakery.base.MVPBakery;
import eu.darken.mvpbakery.base.ViewModelRetainer;
import eu.darken.mvpbakery.injection.ComponentSource;
import eu.darken.mvpbakery.injection.InjectedPresenter;
import eu.darken.mvpbakery.injection.ManualInjector;
import eu.darken.mvpbakery.injection.PresenterInjectionCallback;
import eu.darken.mvpbakery.injection.fragment.HasManualFragmentInjector;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View, HasManualFragmentInjector {

    @Inject ComponentSource<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BaseAppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        MVPBakery.<MainActivityPresenter.View, MainActivityPresenter>builder()
                .presenterFactory(new InjectedPresenter<>(this))
                .presenterRetainer(new ViewModelRetainer<>(this))
                .addPresenterCallback(new PresenterInjectionCallback<>(this))
                .attach(this);

        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public ManualInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void showExampleFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) fragment = ExampleFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commitAllowingStateLoss();
    }
}
