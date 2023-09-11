package com.radioccc.yetanotherpingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "myapp.db";
    private static final int DATABASE_VERSION = 1;

    // Nombres de las tablas
    private static final String TABLE_MAIN = "mainTable";
    private static final String TABLE_CONFIG = "configurationTable";

    // Nombres de las columnas de la tabla principal (mainTable)
    private static final String COL_MAIN_ID = "ID";
    private static final String COL_MAIN_NAME = "name";
    private static final String COL_MAIN_TYPE = "type";
    private static final String COL_MAIN_HOSTNAME = "hostname";
    private static final String COL_MAIN_COMMENT = "comment";

    // Nombres de las columnas de la tabla de configuración (configurationTable)
    private static final String COL_CONFIG_ID = "ID";
    private static final String COL_CONFIG_TIMER = "timer";
    private static final String COL_CONFIG_HTTP_TYPE = "httpType";
    private static final String COL_CONFIG_ALERT = "alert";
    private static final String COL_CONFIG_LAST_CHECK = "lastCheck";
    private static final String COL_CONFIG_RECORD_LIMIT = "recordLimit";

    // Sentencias SQL para crear las tablas
    private static final String CREATE_TABLE_MAIN = "CREATE TABLE " + TABLE_MAIN + " (" +
            COL_MAIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_MAIN_NAME + " TEXT UNIQUE," +
            COL_MAIN_TYPE + " INTEGER," +
            COL_MAIN_HOSTNAME + " TEXT," +
            COL_MAIN_COMMENT + " TEXT)";

    private static final String CREATE_TABLE_CONFIG = "CREATE TABLE " + TABLE_CONFIG + " (" +
            COL_CONFIG_ID + " INTEGER PRIMARY KEY," +
            COL_CONFIG_TIMER + " INTEGER," +
            COL_CONFIG_HTTP_TYPE + " INTEGER," +
            COL_CONFIG_ALERT + " BOOLEAN," +
            COL_CONFIG_LAST_CHECK + " DATE," +
            COL_CONFIG_RECORD_LIMIT + " INTEGER," +
            "FOREIGN KEY (" + COL_CONFIG_ID + ") REFERENCES " + TABLE_MAIN + " (" + COL_MAIN_ID + ") ON DELETE CASCADE ON UPDATE CASCADE)";

    // Contexto de la aplicación
    private final Context context;

    // Ayudante de la base de datos y objeto SQLiteDatabase
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Constructor
    public DatabaseUtils(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    // Clase interna para ayudar con la creación y actualización de la base de datos
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // Método llamado al crear la base de datos
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_MAIN);
            db.execSQL(CREATE_TABLE_CONFIG);
        }

        // Método llamado al actualizar la base de datos (puedes manejar la actualización aquí)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Puedes manejar la actualización de la base de datos aquí si cambias la versión.
        }
    }

    // Abre la base de datos en modo escritura
    public DatabaseUtils open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Cierra la base de datos
    public void close() {
        DBHelper.close();
    }

    // Inserta un nuevo registro en la tabla principal (mainTable)
    public long insertMain(String name, int type, String hostname, String comment) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_MAIN_NAME, name);
        initialValues.put(COL_MAIN_TYPE, type);
        initialValues.put(COL_MAIN_HOSTNAME, hostname);
        initialValues.put(COL_MAIN_COMMENT, comment);
        return db.insert(TABLE_MAIN, null, initialValues);
    }

    // Inserta un nuevo registro en la tabla de configuración (configurationTable)
    public long insertConfig(long id, int timer, int httpType, boolean alert, String lastCheck, int recordLimit) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COL_CONFIG_ID, id);
        initialValues.put(COL_CONFIG_TIMER, timer);
        initialValues.put(COL_CONFIG_HTTP_TYPE, httpType);
        initialValues.put(COL_CONFIG_ALERT, alert);
        initialValues.put(COL_CONFIG_LAST_CHECK, lastCheck);
        initialValues.put(COL_CONFIG_RECORD_LIMIT, recordLimit);
        return db.insert(TABLE_CONFIG, null, initialValues);
    }

    // Elimina un registro de la tabla principal (mainTable)
    public boolean deleteMain(long id) {
        return db.delete(TABLE_MAIN, COL_MAIN_ID + "=" + id, null) > 0;
    }

    // Elimina un registro de la tabla de configuración (configurationTable)
    public boolean deleteConfig(long id) {
        return db.delete(TABLE_CONFIG, COL_CONFIG_ID + "=" + id, null) > 0;
    }

    // Obtiene todos los registros de la tabla principal (mainTable)
    public Cursor getAllMain() {
        return db.query(TABLE_MAIN, new String[]{COL_MAIN_ID, COL_MAIN_NAME, COL_MAIN_TYPE, COL_MAIN_HOSTNAME, COL_MAIN_COMMENT},
                null, null, null, null, null);
    }

    // Obtiene todos los registros de la tabla de configuración (configurationTable)
    public Cursor getAllConfig() {
        return db.query(TABLE_CONFIG, new String[]{COL_CONFIG_ID, COL_CONFIG_TIMER, COL_CONFIG_HTTP_TYPE, COL_CONFIG_ALERT, COL_CONFIG_LAST_CHECK, COL_CONFIG_RECORD_LIMIT},
                null, null, null, null, null);
    }

    // Obtiene un solo elemento de la tabla principal (mainTable)
    public Cursor getMain(long id) throws SQLException {
        Cursor mCursor =
                db.query(true, TABLE_MAIN, new String[]{COL_MAIN_ID, COL_MAIN_NAME, COL_MAIN_TYPE, COL_MAIN_HOSTNAME, COL_MAIN_COMMENT},
                        COL_MAIN_ID + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Obtiene un solo elemento de la tabla de configuración (configurationTable)
    public Cursor getConfig(long id) throws SQLException {
        Cursor mCursor =
                db.query(true, TABLE_CONFIG, new String[]{COL_CONFIG_ID, COL_CONFIG_TIMER, COL_CONFIG_HTTP_TYPE, COL_CONFIG_ALERT, COL_CONFIG_LAST_CHECK, COL_CONFIG_RECORD_LIMIT},
                        COL_CONFIG_ID + "=" + id, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // Actualiza un registro en la tabla principal (mainTable)
    public boolean updateMain(long id, String name, int type, String hostname, String comment) {
        ContentValues args = new ContentValues();
        args.put(COL_MAIN_NAME, name);
        args.put(COL_MAIN_TYPE, type);
        args.put(COL_MAIN_HOSTNAME, hostname);
        args.put(COL_MAIN_COMMENT, comment);
        return db.update(TABLE_MAIN, args, COL_MAIN_ID + "=" + id, null) > 0;
    }

    // Actualiza un registro en la tabla de configuración (configurationTable)
    public boolean updateConfig(long id, int timer, int httpType, boolean alert, String lastCheck, int recordLimit) {
        ContentValues args = new ContentValues();
        args.put(COL_CONFIG_TIMER, timer);
        args.put(COL_CONFIG_HTTP_TYPE, httpType);
        args.put(COL_CONFIG_ALERT, alert);
        args.put(COL_CONFIG_LAST_CHECK, lastCheck);
        args.put(COL_CONFIG_RECORD_LIMIT, recordLimit);
        return db.update(TABLE_CONFIG, args, COL_CONFIG_ID + "=" + id, null) > 0;
    }

}

