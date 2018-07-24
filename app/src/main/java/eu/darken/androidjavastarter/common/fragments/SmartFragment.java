package eu.darken.androidjavastarter.common.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

import butterknife.Unbinder;
import eu.darken.androidjavastarter.App;
import timber.log.Timber;


public class SmartFragment extends Fragment {
    private final String tag;

    {
        tag = App.logTag("Fragment", this.getClass().getSimpleName() + "(" + Integer.toHexString(hashCode()) + ")");
    }

    private final Set<Unbinder> unbinders = new HashSet<>();

    public void addUnbinder(Unbinder unbinder) {
        unbinders.add(unbinder);
    }

    @Override
    public void onAttach(Context context) {
        Timber.tag(tag).v("onAttach(context=" + context + ")");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.tag(tag).v("onCreate(savedInstanceState=" + savedInstanceState + ")");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Timber.tag(tag).v("onViewCreated(view=" + view + ", savedInstanceState=" + savedInstanceState + ")");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Timber.tag(tag).v("onActivityCreated(savedInstanceState=" + savedInstanceState + ")");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Timber.tag(tag).v("onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Timber.tag(tag).v("onPause()");
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Timber.tag(tag).v("onDestroyView()");
        super.onDestroyView();

        for (Unbinder u : unbinders) u.unbind();
        unbinders.clear();
    }

    @Override
    public void onDetach() {
        Timber.tag(tag).v("onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Timber.tag(tag).v("onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.tag(tag).v("onActivityResult(requestCode=%d, resultCode=%d, data=%s)", requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
