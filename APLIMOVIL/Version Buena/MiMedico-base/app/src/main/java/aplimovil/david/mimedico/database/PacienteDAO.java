package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.models.MedPac;
import aplimovil.david.mimedico.models.Medico;
import aplimovil.david.mimedico.models.Paciente;

/**
 * Created by david on 6/10/2015.
 */
public class PacienteDAO {
    public static final String TBL_NAME="planeta";
    public static final String C_PAC="paciente";
    public static final String C_CED="cedula";
    public static final String C_MAIL="correo";
    public static final String C_USR="usuario";
    public static final String C_PASS="password";
    public static final Paciente PACI = new Paciente();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    SQLiteDatabase db;

    public PacienteDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertPaciente(Paciente p){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getPaciente());

        cV.put(C_MAIL, p.getCorreo());
        cV.put(C_PAC, p.getPaciente());
        cV.put(C_USR, p.getUsuario());
        cV.put(C_PASS, p.getPassword());
        db.insert(TBL_NAME, null, cV);
        //long id = db.insert(TBL_NAME, null, cV);
        //p.setId(id);
    }

    public void updatePaciente(Paciente p){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getPaciente());

        cV.put(C_MAIL, p.getCorreo());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + p.getId()});
    }

    public void deletePaciente(long id){

        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});

    }
    public Paciente inicioSesion(String usr, String pass){
        //String sql = "SELECT * FROM "+TBL_NAME+" WHERE "+C_USR+"='"+usr+"' AND "+C_PASS+"='"+pass+"'";
        String sql = "SELECT * FROM "+TBL_NAME;

        return cursorToList1(sql, usr, pass);
    }


    public List<Paciente> getAllPaciente2(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return cursorToList(sql);

    }
    public List<Paciente> getAllPaciente(Context context,Long id){
        String sql = "SELECT * FROM "+TBL_NAME;
        List<Paciente> data1 = new ArrayList<>();
        List<MedPac> data2 = new ArrayList<>();
        data1=  cursorToList(sql);
        MedPacDAO dao = new MedPacDAO(context);
        data2 = dao.getAllMedPac(id);
        for(int i =0;i<data1.size();i++){
            for(int j=0;j<data2.size();j++){
                if(data1.get(i).getId()==data2.get(j).getPaciente()){
                    data1.remove(i);
                }
            }

        }
        return data1;
    }

    public List<Paciente> getAllPaciente1(Context context,Long id){
        String sql = "SELECT * FROM "+TBL_NAME;
        List<Paciente> data1 = new ArrayList<>();
        List<MedPac> data2 = new ArrayList<>();
        List<Paciente> res = new ArrayList<>();
        data1=  cursorToList(sql);
        MedPacDAO dao = new MedPacDAO(context);
        data2 = dao.getAllMedPac(id);
        if(data2.isEmpty()){
            Log.i("Hola","Esta vacio");
        }else {
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getId() == data2.get(j).getPaciente()) {
                        res.add(data1.get(i));
                    }
                }

            }

        }
        return res;
    }

    private List<Paciente> cursorToList(String sql){
        List<Paciente> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            Paciente p = new Paciente();
            p.setId(cursor.getLong(0));
            p.setPaciente(cursor.getString(1));
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
    private Paciente cursorToList1(String sql, String usr, String pass) {

        try {
            Cursor cursor = db.rawQuery(sql, null);
            Paciente p = new Paciente();

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);

                if (cursor.getString(2).equals(usr) && cursor.getString(3).equals(pass)) {
                    Log.i("TratamientoDao", "Lo encontro");
                    p.setId(cursor.getLong(0));
                    p.setPaciente(cursor.getString(1));
                    p.setUsuario(cursor.getString(2));
                    p.setPassword(cursor.getString(3));
                    p.setCorreo(cursor.getString(4));
                    return p;
                }

            }
            p.setId(0);
            return p;
        }catch (NullPointerException e){
            Paciente p = new Paciente();
            p.setId(0);
            return p;
        }
    }
}
