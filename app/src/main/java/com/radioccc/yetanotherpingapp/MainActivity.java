package com.radioccc.yetanotherpingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextIP;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchServerOrWeb;
    private Button btnTest;
    private ListView listViewResults;
    private ArrayAdapter<String> resultsAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa las vistas y adaptadores
        editTextIP = findViewById(R.id.editTextIP);
        switchServerOrWeb = findViewById(R.id.switchServerOrWeb);
        btnTest = findViewById(R.id.btnTest);
        listViewResults = findViewById(R.id.listViewResults);
        resultsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewResults.setAdapter(resultsAdapter);

        editTextIP.setText("google.cl");

        // Configura el Listener del botón
        btnTest.setOnClickListener(v -> testServerOrWebAvailability());

        // Configura el Listener del interruptor
        switchServerOrWeb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Cambia el texto del botón según el estado del interruptor
                if (isChecked) {
                    btnTest.setText("Probar Web");
                } else {
                    btnTest.setText("Probar Servidor");
                }
            }
        });
    }

    private void testServerOrWebAvailability() {
        final String inputText = "https://" + editTextIP.getText().toString();

        // Verifica el estado del interruptor y realiza la verificación correspondiente en un hilo
        if (switchServerOrWeb.isChecked()) {
            // El interruptor está en ON, verifica la página web en un hilo
            new Thread(() -> {
                final int result = MonitorUtils.checkWebsiteAvailability(inputText);
                // Actualiza la interfaz de usuario en el hilo principal
                runOnUiThread(() -> {
                    if (result != -1) {
                        String[] httpcode = HttpStatusUtils.getHttpStatusInfo(result);
                        String resultText = "Código de respuesta HTTP: " + httpcode[0] + "\n" + httpcode[1] + "\n" + httpcode[2];
                        resultsAdapter.add(resultText);
                    } else {
                        resultsAdapter.add("Error al conectar a la página web.");
                    }
                });
            }).start();
        } else {
            // El interruptor está en OFF, verifica el servidor en un hilo
            //new CheckServerTask().execute(inputText);
        }
    }
}
