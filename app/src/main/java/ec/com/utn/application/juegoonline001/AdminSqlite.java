package ec.com.utn.application.juegoonline001;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqlite extends SQLiteOpenHelper {


    public AdminSqlite(@Nullable Context context,
                       @Nullable String name,
                       @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS juegos(" +
                "id INT NOT NULL PRIMARY KEY," +
                "nombre TEXT(20)," +
                "plataforma TEXT(50)," +
                "tipo_juego TEXT(50)," +
                "edad_permitida INT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
