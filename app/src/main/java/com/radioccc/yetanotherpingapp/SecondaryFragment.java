package com.radioccc.yetanotherpingapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import es.dmoral.toasty.Toasty;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SecondaryFragment extends Fragment {

    private EditText editTextName;
    private Spinner spinnerType;
    private EditText editTextHostname;
    private EditText editTextComment;
    private Button addButton;
    private Spinner spinnerDeleteItem;
    private Button deleteItemButton;
    private Button deleteAllButton;
    private ListView listViewResults;
    private ArrayAdapter<String> resultsAdapter;

    // Declarar una instancia de DatabaseUtils para trabajar con la base de datos
    private DatabaseUtils databaseUtils;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secondary, container, false);

        // Inicializar vistas y adaptador
        editTextName = view.findViewById(R.id.addItemBD);
        spinnerType = view.findViewById(R.id.selectHostTypeDB);
        editTextHostname = view.findViewById(R.id.addHostnameIPDB);
        editTextComment = view.findViewById(R.id.addCommentHostDB);
        addButton = view.findViewById(R.id.buttonAddItemDB);
        listViewResults = view.findViewById(R.id.listviewItemDB);
        spinnerDeleteItem = view.findViewById(R.id.selectDeleteItem);
        deleteItemButton = view.findViewById(R.id.buttonDeleteItem);
        deleteAllButton = view.findViewById(R.id.buttonDeleteBD);
        resultsAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1);
        listViewResults.setAdapter(resultsAdapter);

        // Crea un ArrayAdapter con los elementos que deseas agregar
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item);
        adapter.add(getString(R.string.server));
        adapter.add(getString(R.string.webpage));

        // Establece el adaptador en el Spinner
        spinnerType.setAdapter(adapter);

        // Inicializar la instancia de DatabaseUtils
        databaseUtils = new DatabaseUtils(requireContext());

        deleteItemButton.setOnClickListener(v -> {
            String item = (String) spinnerDeleteItem.getSelectedItem();

            try {
                boolean deletionSuccess = databaseUtils.deleteMainByName(item);

                if (deletionSuccess){
                    loadDatabaseRecords();
                    updateSpinner();
                    Toasty.info(requireContext(), "Item '" + item + "' eliminado", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("DatabaseUtils", "Error al eliminar el item en la base de datos.");
                }
            } catch (SQLException e){
                Toasty.error(requireContext(), "Error SQL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        deleteAllButton.setOnClickListener(v -> {
            try {
                databaseUtils.open();
                boolean del = databaseUtils.deleteAllMainEntries();

                if (del){
                    loadDatabaseRecords();
                    updateSpinner();
                    Toasty.info(requireContext(), "Todos los elementos han sido eliminados", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("DatabaseUtils", "Error al eliminar BD");
                }
            } catch (SQLException e){
                Toasty.error(requireContext(), "Error SQL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                databaseUtils.close();
            }
        });

// Configurar el botón para agregar registros a la base de datos y actualizar la lista
        addButton.setOnClickListener(v -> {
            // Obtener los valores de los campos de entrada
            String name = editTextName.getText().toString();
            int type = spinnerType.getSelectedItemPosition(); // Usar la posición seleccionada como tipo
            String hostname = editTextHostname.getText().toString();
            String comment = editTextComment.getText().toString();

            // Realizar validación de campos
            if (!name.isEmpty() && !hostname.isEmpty()) {
                // Verificar si el nombre ya existe en la base de datos
                boolean nameExists = databaseUtils.checkNameExists(name);

                if (!nameExists) {
                    // Insertar el registro en la base de datos
                    try {
                        databaseUtils.open(); // Abre la base de datos antes de realizar operaciones en ella.

                        long id = databaseUtils.insertMain(name, type, hostname, comment);

                        if (id != -1) {
                            String record = "ID: " + id + ", Name: " + name + ", Type: " + type + ", Hostname: " + hostname + ", Comment: " + comment;
                            resultsAdapter.add(record);

                            // Limpiar los campos de entrada después de agregar
                            editTextName.getText().clear();
                            spinnerType.setSelection(0); // Restablecer la selección del Spinner
                            editTextHostname.getText().clear();
                            editTextComment.getText().clear();
                        } else {
                            // Ocurrió un error al insertar el registro.
                            Log.e("DatabaseUtils", "Error al insertar el registro en la base de datos.");
                        }

                    } catch (SQLException e) {
                        Log.e("DatabaseUtils", "Error al abrir la base de datos: " + e.getMessage());
                    } finally {
                        databaseUtils.close(); // Cierra la base de datos cuando hayas terminado de usarla.
                        loadDatabaseRecords();
                        updateSpinner();
                    }
                } else {
                    // El nombre ya existe en la base de datos, muestra un mensaje de error
                    //editTextName.setError("El nombre ya existe en la base de datos");
                    Toasty.error(requireContext(), "El nombre ya existe en la base de datos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Algunos campos están vacíos, muestra un mensaje de error
                if (name.isEmpty()) {
                    editTextName.setError("Este campo no puede estar vacío");
                }
                if (hostname.isEmpty()) {
                    editTextHostname.setError("Este campo no puede estar vacío");
                }
            }
        });



        // Cargar registros existentes de la base de datos y mostrarlos en la lista
        loadDatabaseRecords();
        updateSpinner();

        return view;
    }

    private void updateSpinner() {
        List<String> names = databaseUtils.getAllNamesFromMain();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, names);
        spinnerDeleteItem.setAdapter(spinnerAdapter);
    }

    private List<String> cursorToList(Cursor cursor) {
        List<String> records = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener los valores de las columnas que desees agregar a la lista
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtils.COL_MAIN_NAME));
                int type = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseUtils.COL_MAIN_TYPE));
                String hostname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtils.COL_MAIN_HOSTNAME));
                String comment = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUtils.COL_MAIN_COMMENT));

                // Crear una cadena con los valores y agregarla a la lista
                String record = "Name: " + name + ", Type: " + type + ", Hostname: " + hostname + ", Comment: " + comment;
                records.add(record);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close(); // Cerrar el cursor cuando hayas terminado con él
        }
        return records;
    }

    // Dentro de la función loadDatabaseRecords
    private void loadDatabaseRecords() {
        // Abre la base de datos
        databaseUtils.open();

        // Obtiene todos los registros de la tabla principal
        Cursor cursor = databaseUtils.getAllMain();

        // Convierte el cursor a una lista de cadenas
        List<String> records = cursorToList(cursor);

        // Cierra la base de datos
        databaseUtils.close();

        // Agrega los registros a la lista
        resultsAdapter.clear();
        resultsAdapter.addAll(records);
    }

}
