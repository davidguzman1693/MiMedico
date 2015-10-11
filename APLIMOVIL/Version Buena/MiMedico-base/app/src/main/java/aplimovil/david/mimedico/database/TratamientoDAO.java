package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.TrataActivity;
import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Tratamiento;

/**
 * Created by david on 6/10/2015.
 */
public class TratamientoDAO {
    public static final String TBL_NAME="tratamiento";
    public static final String C_TRATA= "tratamiento";
    public static final String C_FINALTRA="finaltratamiento";
    public static final String C_FINICIO="fechainicio";
    public static final String C_FFIN="fechafin";
    public static final String C_HORA="horario";
    public static final String C_COND="condicion";
    public static final String C_CONTR="control";
    public static final String C_MED="medico";

    SQLiteDatabase db;

    public TratamientoDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertTratamiento(Tratamiento t,long m){
        Log.i("Info",""+t.getTratamiento());
        ContentValues cV = new ContentValues();
        cV.put(C_MED, m);
        cV.put(C_TRATA, t.getTratamiento());
        cV.put(C_FINALTRA, t.getFinaltratamiento());
        cV.put(C_FINICIO, t.getFechainicio());
        cV.put(C_FFIN, t.getFechafin());
        cV.put(C_HORA, t.getHorario());
        cV.put(C_COND, t.getCondicion());
        cV.put(C_CONTR, t.getControl());

        long id = db.insert(TBL_NAME, null, cV);
        t.setId(id);
    }
    public void updateTratamiento(Tratamiento t){
        ContentValues cV = new ContentValues();
        cV.put(C_TRATA, t.getTratamiento());
        cV.put(C_FINALTRA, t.getFinaltratamiento());
        cV.put(C_FINICIO, t.getFechainicio());
        cV.put(C_FFIN, t.getFechafin());
        cV.put(C_HORA, t.getHorario());
        cV.put(C_COND, t.getCondicion());
        cV.put(C_CONTR, t.getControl());

        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + t.getId()});
    }

    public void deleteTratamiento(long id){
        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});
    }

    public List<Tratamiento> getAllTratamiento(Context context, long id, long m){
        String sql = "SELECT * FROM "+TBL_NAME;
        List<Tratamiento> data1 = new ArrayList<>();
        List<PacTra> data2 = new ArrayList<>();

        data1 = cursorToList1(sql,m);
        PacTraDAO dao = new PacTraDAO(context);
        data2 = dao.getAllPacTra(id);
        if(data1.isEmpty()){
            Log.i("Hola","Esta vacio");
        }
        else{
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getId() == data2.get(j).getPaciente()) {
                        data1.remove(i);
                    }
                }

            }
        }
            return  data1;
    }

    public List<Tratamiento> getAllTratamiento1(long id){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList1(sql, id);
    }

    public List<Tratamiento> getAllTratamiento2(Context context, long id){
        String sql = "SELECT * FROM "+TBL_NAME;
        List<Tratamiento> data1 = new ArrayList<>();
        List<Tratamiento> res = new ArrayList<>();
        List<PacTra> data2 = new ArrayList<>();

        data1 = cursorToList(sql);
        PacTraDAO dao = new PacTraDAO(context);
        data2 = dao.getAllPacTra(id);
        if(data1.isEmpty()){
            Log.i("Hola","Esta vacio");
        }
        else{
            for (int i = 0; i < data1.size(); i++) {
                for (int j = 0; j < data2.size(); j++) {
                    if (data1.get(i).getId() == data2.get(j).getPaciente()) {
                        res.add(data1.get(i));
                    }
                }

            }
        }
        return  res;
    }

    private List<Tratamiento> cursorToList(String sql){
        List<Tratamiento> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {

            do {

                Tratamiento tratami = new Tratamiento();
                tratami.setId(cursor.getLong(0));
                tratami.setTratamiento(cursor.getString(2));
                tratami.setFinaltratamiento(cursor.getString(3));
                tratami.setCondicion(cursor.getString(4));
                tratami.setFechainicio(cursor.getString(5));
                tratami.setFechafin(cursor.getString(6));
                tratami.setHorario(cursor.getString(7));
                tratami.setControl(cursor.getString(8));
                data.add(tratami);
                Log.i("Planeta", "ID:" + cursor.getLong(0));
                Log.i("Planeta", "TRAMIENTO:" + cursor.getString(2));
                Log.i("Planeta", "FECHAFIN:" + cursor.getString(3));
                Log.i("Planeta", "COND:" + cursor.getString(4));
                Log.i("Planeta", "FINICIO:" + cursor.getString(5));
                Log.i("Planeta", "FFIN:" + cursor.getString(6));
                Log.i("Planeta", "HORA:" + cursor.getString(7));
                Log.i("Planeta", "CONTR:" + cursor.getString(8));
            }while (cursor.moveToNext());
        }

        return data;
    }

    private List<Tratamiento> cursorToList1(String sql,long m){
        List<Tratamiento> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            if(cursor.getLong(1)==m){
            Tratamiento tratami = new Tratamiento();
            tratami.setId(cursor.getLong(0));
            tratami.setTratamiento(cursor.getString(2));
            tratami.setFinaltratamiento(cursor.getString(3));
            tratami.setCondicion(cursor.getString(4));
            tratami.setFechainicio(cursor.getString(5));
            tratami.setFechafin(cursor.getString(6));
            tratami.setHorario(cursor.getString(7));
            tratami.setControl(cursor.getString(8));
            data.add(tratami);

            }

        }
/*
        for(int i=0; i<data.size();i++){
            Log.i("Planeta", "----------------");
            Log.i("Planeta", "ID:" + data.get(i).getId());
            Log.i("Planeta", "TRAMIENTO:" + data.get(i).getTratamiento());
            Log.i("Planeta", "FECHAFIN:" + data.get(i).getFechafin());
            Log.i("Planeta", "HORARIO:" + data.get(i).getHorario());
        }
*/
        return data;
    }

}
