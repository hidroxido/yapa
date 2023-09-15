package com.radioccc.yetanotherpingapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Devuelve el fragmento correspondiente a la posición.
        if (position == 0) {
            return new MainFragment();
        } else {
            return new SecondaryFragment();
        }
    }

    @Override
    public int getItemCount() {
        // Devuelve el número total de fragmentos (en este caso, 2)
        return 2;
    }
}
