package eu.darken.androidjavastarter.main.ui;


import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import dagger.android.AndroidInjector;
import eu.darken.androidjavastarter.ExampleApplicationMock;
import eu.darken.androidjavastarter.R;
import eu.darken.androidjavastarter.main.ui.fragment.ExampleFragmentComponent;
import eu.darken.androidjavastarter.main.ui.fragment.ExampleFragmentPresenter;
import eu.darken.mvpbakery.injection.ComponentSource;
import eu.darken.mvpbakery.injection.ManualInjector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    ExampleApplicationMock app;

    @Mock MainActivityPresenter mainPresenter;
    @Mock MainActivityComponent mainComponent;

    @Mock ComponentSource<Fragment> fragmentInjector;
    @Mock ExampleFragmentPresenter exampleFragmentPresemter;
    @Mock ExampleFragmentComponent exampleFragmentComponent;

    @Before
    public void setUp() {
        app = (ExampleApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.setActivityComponentSource(new ActivityInjector());

        doAnswer(invocation -> {
            MainActivity mainActivity = invocation.getArgument(0);
            mainActivity.fragmentInjector = fragmentInjector;
            return null;
        }).when(mainComponent).inject(any());
        when(mainComponent.getPresenter()).thenReturn(mainPresenter);
        when(mainPresenter.getComponent()).thenReturn(mainComponent);


        doAnswer(invocation -> {
            // Nothing to inject atm
            return null;
        }).when(exampleFragmentComponent).inject(any());
        when(fragmentInjector.get(any())).then(invocation -> exampleFragmentComponent);
        when(exampleFragmentComponent.getPresenter()).thenReturn(exampleFragmentPresemter);
        when(exampleFragmentPresemter.getComponent()).thenReturn(exampleFragmentComponent);
    }

    public class ActivityInjector implements ManualInjector<Activity> {

        @Override
        public AndroidInjector get(Activity instance) {
            return mainComponent;
        }

        @Override
        public void inject(Activity instance) {
            mainComponent.inject((MainActivity) instance);
        }

    }

    @Test
    public void checkFragmentShowing() throws Throwable {
        activityRule.launchActivity(null);

        activityRule.runOnUiThread(() -> activityRule.getActivity().showExampleFragment());

        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }
}
