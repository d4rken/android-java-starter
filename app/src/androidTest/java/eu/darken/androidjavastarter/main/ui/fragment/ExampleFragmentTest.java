package eu.darken.androidjavastarter.main.ui.fragment;

import android.support.v4.app.Fragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import dagger.android.AndroidInjector;
import eu.darken.androidjavastarter.R;
import eu.darken.mvpbakery.injection.ManualInjector;
import testhelper.FragmentTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleFragmentTest {
    @Rule public FragmentTestRule<ExampleFragment> fragmentRule = new FragmentTestRule<>(ExampleFragment.class);

    @Mock ExampleFragmentPresenter presenter;
    @Mock ExampleFragmentComponent component;


    private ManualInjector<Fragment> injector = new ManualInjector<Fragment>() {
        @Override
        public AndroidInjector get(Fragment fragment) {
            return component;
        }

        @Override
        public void inject(Fragment fragment) {

        }
    };

    @Before
    public void setUp() {
        doAnswer(invocation -> {
            ExampleFragment exampleFragment = invocation.getArgument(0);
            exampleFragment.presenter = presenter;
            return null;
        }).when(component).inject(any());
        when(component.getPresenter()).thenReturn(presenter);

        doAnswer(invocationOnMock -> null).when(presenter).onBindChange(any());
        when(presenter.getComponent()).thenReturn(component);
        fragmentRule.setManualInjector(injector);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testShowEmoji() throws Throwable {
        fragmentRule.launchActivity(null);
        onView(withId(R.id.emoji_text)).check(matches(withText("Hello World!")));
        fragmentRule.runOnUiThread(() -> fragmentRule.getFragment().showEmoji("test"));
        onView(withId(R.id.emoji_text)).check(matches(withText("test")));
    }

    @Test
    public void testFABClick() {
        fragmentRule.launchActivity(null);

        onView(withId(R.id.fab)).perform(click());
        verify(presenter).onGetNewEmoji();
    }

}
