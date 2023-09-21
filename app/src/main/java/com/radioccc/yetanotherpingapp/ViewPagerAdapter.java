package com.radioccc.yetanotherpingapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 4;
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Devuelve el fragmento correspondiente a la posición.
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new DBMainTestFragment();
            case 2:
                return new DBConfigTestFragment();
            case 3:
                return new ListviewTestFragment(); // Agrega otro fragmento
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        // Devuelve el número total de fragmentos (en este caso, 2)
        return NUM_TABS;
    }
}
