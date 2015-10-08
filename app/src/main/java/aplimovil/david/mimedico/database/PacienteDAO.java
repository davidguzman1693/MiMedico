package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.models.Paciente;

/**
 * Created by david on 6/10/2015.
 */
public class PacienteDAO {
    public static final String TBL_NAME="planeta";
    public static final String C_PAC="paciente";
    public static final String C_CED="cedula";
    public static final String C_MAIL="correo";


    SQLiteDatabase db;

    public PacienteDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertPaciente(Paciente p){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getPaciente());
        cV.put(C_CED, p.getCedula());
        cV.put(C_MAIL, p.getCorreo());


        db.insert(TBL_NAME, null, cV);
        //long id = db.insert(TBL_NAME, null, cV);
        //p.setId(id);
    }

    public void updatePaciente(Paciente p){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getPaciente());
        cV.put(C_CED, p.getCedula());
        cV.put(C_MAIL, p.getCorreo());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + p.getId()});
    }

    public void deletePaciente(long id){

        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});

    }

    public List<Paciente> getAllPaciente(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    private List<Paciente> cursorToList(String sql){
        List<Paciente> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            Paciente p = new Paciente();
            p.setId(i+1);
            p.setPaciente(cursor.getString(1));
            p.setCedula(cursor.getString(2));
            p.setCorreo(cursor.getString(3));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getColumnName(0));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getShort(0));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getInt(0));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getLong(0));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getString(0));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getColumnIndex("_id"));
            Log.i("TratamientoDao", "Este es el id: " + cursor.getColumnIndexOrThrow("_id"));
            Log.i("TratamientoDao","Este es el id1: "+cursor.getString(1));
            Log.i("TratamientoDao","Este es el id2: "+cursor.getString(2));
            Log.i("TratamientoDao","Este es el id3: "+cursor.getString(3));
            data.add(p);
        }
        return data;
    }
}
