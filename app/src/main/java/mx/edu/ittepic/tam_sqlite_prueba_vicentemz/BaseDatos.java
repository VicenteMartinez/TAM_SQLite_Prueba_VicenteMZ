package mx.edu.ittepic.tam_sqlite_prueba_vicentemz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vicen on 12/11/2017.
 */

//ayudante,asistente de la conexion , es un midleware(SQLite)
public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  CLIENTE(" +
                "IDCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "NOMBRE VARCHAR(200)," +
                "DOMICILIO VARCHAR(400)," +
                "COLONIA VARCHAR(100))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
