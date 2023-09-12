package com.radioccc.yetanotherpingapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Aquí debes devolver el fragmento correspondiente.
        switch (position) {
            case 0:
                return new MainFragment(); // Reemplaza con tus fragmentos
            // Agrega más casos para más fragmentos si es necesario.
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Devuelve el número total de fragmentos que tienes.
        return 1; // Cambia esto según la cantidad de fragmentos que tengas.
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Devuelve el título de las pestañas, si es necesario.
        switch (position) {
            case 0:
                return "Main"; // Reemplaza con los títulos deseados.
            default:
                return "";
        }
    }
}

