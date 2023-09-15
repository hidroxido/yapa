package com.radioccc.yetanotherpingapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el ViewPager2
        ViewPager2 viewPager = findViewById(R.id.viewPager2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter fragmentAdapter = new ViewPagerAdapter(fragmentManager, getLifecycle());
        viewPager.setAdapter(fragmentAdapter);

        // Configurar el TabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Personalizar los nombres de las pestañas aquí
            if (position == 0) {
                tab.setText("Tab 1");
            } else {
                tab.setText("Tab 2");
            }
        }).attach();
    }
}
