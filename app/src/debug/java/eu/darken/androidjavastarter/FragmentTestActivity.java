package eu.darken.androidjavastarter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import eu.darken.mvpbakery.injection.ManualInjector;
import eu.darken.mvpbakery.injection.fragment.HasManualFragmentInjector;

public class FragmentTestActivity extends AppCompatActivity implements HasManualFragmentInjector {

    ManualInjector<Fragment> manualInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout view = new LinearLayout(this);
        //noinspection ResourceType
        view.setId(1);
        setContentView(view);
    }

    public void setManualInjector(ManualInjector<Fragment> manualInjector) {
        this.manualInjector = manualInjector;
    }

    @Override
    public ManualInjector<Fragment> supportFragmentInjector() {
        return manualInjector;
    }

}
