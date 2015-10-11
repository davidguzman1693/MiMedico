package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.models.Medico;
import aplimovil.david.mimedico.models.Paciente;

/**
 * Created by david on 10/10/2015.
 */
public class MedicoDAO {
    public static final String TBL_NAME="medico";
    public static final String C_PAC="medico";

    public static final String C_MAIL="correo";
    public static final String C_USR="usuario";
    public static final String C_PASS="password";


    SQLiteDatabase db;

    public MedicoDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertMedico(Medico p){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getMedico());
        cV.put(C_USR, p.getUsuario());
        cV.put(C_PASS, p.getPassword());
        cV.put(C_MAIL, p.getCorreo());
        db.insert(TBL_NAME, null, cV);
        //long id = db.insert(TBL_NAME, null, cV);
        //p.setId(id);
    }

    public void updateMedico(Medico p){
        ContentValues cV = new ContentValues();

        cV.put(C_PAC, p.getMedico());
        cV.put(C_USR, p.getUsuario());
        cV.put(C_PASS, p.getPassword());
        cV.put(C_MAIL, p.getCorreo());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + p.getId()});
    }

    public void deleteMedico(long id){

        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});

    }

    public List<Medico> getAllMedico(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    public Medico inicioSesion(String usr, String pass){
        //String sql = "SELECT * FROM "+TBL_NAME+" WHERE "+C_USR+"='"+usr+"' AND "+C_PASS+"='"+pass+"'";
        String sql = "SELECT * FROM "+TBL_NAME;

        return cursorToList1(sql, usr, pass);
    }

    private List<Medico> cursorToList(String sql) {
        List<Medico> data = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() == 0) {
            return null;
        } else {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);

                Medico p = new Medico();
                p.setId(cursor.getLong(0));
                p.setMedico(cursor.getString(1));

                p.setCorreo(cursor.getString(3));
                Log.i("TratamientoDao", "Este es el id1: " + cursor.getString(0));
                Log.i("TratamientoDao", "Este es el id2: " + cursor.getString(1));
                Log.i("TratamientoDao", "Este es el id3: " + cursor.getString(2));
                Log.i("TratamientoDao", "Este es el id3: " + cursor.getString(3));
                Log.i("TratamientoDao", "Este es el id3: " + cursor.getString(4));
                data.add(p);
            }
            return data;
        }
    }
    private Medico cursorToList1(String sql, String usr, String pass) {

        try {
            Cursor cursor = db.rawQuery(sql, null);
            Medico p = new Medico();

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);

                if (cursor.getString(2).equals(usr) && cursor.getString(3).equals(pass)) {
                    Log.i("TratamientoDao", "Lo encontro");
                    p.setId(cursor.getLong(0));
                    p.setMedico(cursor.getString(1));
                    p.setUsuario(cursor.getString(2));
                    p.setPassword(cursor.getString(3));
                    p.setCorreo(cursor.getString(4));
                    return p;
                }

            }
            p.setId(0);
            return p;
        }catch (NullPointerException e){
            Medico p = new Medico();
            p.setId(0);
            return p;
        }
    }
}
