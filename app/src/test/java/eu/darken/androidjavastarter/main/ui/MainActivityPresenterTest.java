package eu.darken.androidjavastarter.main.ui;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import eu.darken.androidjavastarter.main.core.SomeRepo;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MainActivityPresenterTest {
    @Rule public MockitoRule rule = MockitoJUnit.rule();
    @Mock MainActivityPresenter.View view;
    @Mock SomeRepo someRepo;

    @Test
    public void testGetEmojis() {
        when(someRepo.isShowFragment()).thenReturn(true);
        MainActivityPresenter mainPresenter = new MainActivityPresenter(someRepo);
        verify(someRepo, never()).isShowFragment();
        mainPresenter.onBindChange(view);
        verify(someRepo).isShowFragment();
        verify(view).showExampleFragment();
    }
}
