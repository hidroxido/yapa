package com.radioccc.yetanotherpingapp;

import android.os.AsyncTask;
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
    private Switch switchServerOrWeb;
    private Button btnTest;
    private ListView listViewResults;
    private ArrayAdapter<String> resultsAdapter;

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

        // Configura el Listener del botón
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testServerOrWebAvailability();
            }
        });

        // Configura el Listener del interruptor
        switchServerOrWeb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        String inputText = editTextIP.getText().toString();

        // Verifica el estado del interruptor y realiza la verificación correspondiente
        if (switchServerOrWeb.isChecked()) {
            // El interruptor está en ON, verifica la página web en segundo plano
            new CheckWebsiteTask().execute(inputText);
        } else {
            // El interruptor está en OFF, verifica el servidor en segundo plano
            //new CheckServerTask().execute(inputText);
        }
    }

    private class CheckWebsiteTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            // Llamar al método para verificar disponibilidad de página web en segundo plano
            String url = params[0];
            return MonitorUtils.checkWebsiteAvailability(url);
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Actualizar la interfaz de usuario con el resultado
            if (result != -1) {
                String resultText = "Código de respuesta HTTP: " + result;
                resultsAdapter.add(resultText);
            } else {
                resultsAdapter.add("Error al conectar a la página web.");
            }
        }
    }
}
