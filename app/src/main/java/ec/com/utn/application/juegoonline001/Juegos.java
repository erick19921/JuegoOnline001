package ec.com.utn.application.juegoonline001;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Juegos {
    AdminSqlite adminDB;
    SQLiteDatabase db;

    public Juegos(Context context, String dbFileName, int version) {
        adminDB = new AdminSqlite(context, dbFileName,null, version );
        db= adminDB.getWritableDatabase();
    }

    public Juego Add(int id, String nombre, String plataforma, String tipo, int edad)
    {
        Juego j= new Juego();
        j.id=id;
        j.nombre=nombre;
        j.plataforma=plataforma;
        j.tipo_jue=tipo;
        j.edad_per=edad;

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nombre", nombre);
        values.put("plataforma", plataforma);
        values.put("tipo_juego", tipo);
        values.put("edad_permitida", edad);

        db.insert("juegos", null, values);
        return j;
    }

    public int Update(int id, String nombre, String plataforma, String tipo, int edad){

        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nombre", nombre);
        values.put("plataforma", plataforma);
        values.put("tipo_juego", tipo);
        values.put("edad_permitida", edad);

        int rowsafected = db.update("juegos", values, "id="+id, null);

        return rowsafected;
    }

    public  int  Delete(int id){
        int rowsdeleted= db.delete("juegos", "id="+id, null);
        return rowsdeleted;
    }

    public Juego SelectOne(int id)
    {
        Cursor c = db.rawQuery("SELECT id, nombre, plataforma, tipo_juego, edad_permitida FROM juegos WHERE id="+id, null);

        if(c.getCount()>0)
        {
            c.moveToFirst();
            Juego j = new Juego();
            j.id=c.getInt(0);
            j.nombre=c.getString(1);
            j.plataforma=c.getString(2);
            j.tipo_jue=c.getString(3);
            j.edad_per=c.getInt(4);

            return j;

        }
        else
            return null;
    }

    public  Juego[]  Select_ByDescripcion(String nombre){
        Cursor c= db.rawQuery("SELECT id, nombre, plataforma, tipo_juego, edad_permitida FROM juegos WHERE nombre LIKE '%"+nombre+"%' ",
                null);

        if(c.getCount()>0)
        {
            Juego j;
            Juego[] data = new Juego[c.getCount()];
            int i=0;
            while (c.moveToNext())
            {
                j = new Juego();
                j.id = c.getInt(0);
                j.nombre = c.getString(1);
                j.plataforma = c.getString(2);
                j.tipo_jue = c.getString(3);
                j.edad_per = c.getInt(4);
                data[i++] = j;
            }
            return data;

        }else
            return null;
    }
}
