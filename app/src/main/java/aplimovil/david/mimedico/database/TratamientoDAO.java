package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.TrataActivity;
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

    SQLiteDatabase db;

    public TratamientoDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertTratamiento(Tratamiento t){
        ContentValues cV = new ContentValues();
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

    public List<Tratamiento> getAllTratamiento(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    private List<Tratamiento> cursorToList(String sql){
        List<Tratamiento> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);

        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            Tratamiento tratami = new Tratamiento();
            tratami.setId(cursor.getLong(0));
            tratami.setTratamiento(cursor.getString(1));
            tratami.setFinaltratamiento(cursor.getString(2));
            tratami.setCondicion(cursor.getString(3));
            tratami.setFechainicio(cursor.getString(4));
            tratami.setFechafin(cursor.getString(5));
            tratami.setHorario(cursor.getString(6));
            tratami.setControl(cursor.getString(7));
            data.add(tratami);
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
