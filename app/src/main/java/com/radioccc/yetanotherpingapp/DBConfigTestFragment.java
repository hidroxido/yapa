package com.radioccc.yetanotherpingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class DBConfigTestFragment extends Fragment {
    private NumberPicker numberPickerTimer;
    private NumberPicker numberPickerRecord;
    private Spinner selectHostType;
    private Spinner spinnerService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dbconfigtest, container, false);
        // Puedes personalizar la vista del fragmento aquí

        numberPickerTimer = view.findViewById(R.id.numberPickerTimer);
        numberPickerRecord = view.findViewById(R.id.numberPickerRecordLimit);
        selectHostType = view.findViewById(R.id.selectHostType);
        spinnerService = view.findViewById(R.id.spinnerService);

        // Establece el valor mínimo y máximo permitido
        numberPickerTimer.setMinValue(1);   // Valor mínimo
        numberPickerTimer.setMaxValue(60);  // Valor máximo
        numberPickerTimer.setValue(5);      // Valor inicial seleccionado
        numberPickerRecord.setMinValue(1);   // Valor mínimo
        numberPickerRecord.setMaxValue(60);  // Valor máximo
        numberPickerRecord.setValue(7);      // Valor inicial seleccionado

        ArrayAdapter<String> adapterHostType = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        adapterHostType.add(getString(R.string.server));
        adapterHostType.add(getString(R.string.webpage));
        // Establece el adaptador en el Spinner
        selectHostType.setAdapter(adapterHostType);

        ArrayAdapter<String> adapterService = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        adapterService.add(getString(R.string.https));
        adapterService.add(getString(R.string.http));
        // Establece el adaptador en el Spinner
        spinnerService.setAdapter(adapterService);

        return view;
    }
}
