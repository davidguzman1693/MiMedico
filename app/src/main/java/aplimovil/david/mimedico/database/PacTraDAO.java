package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;

/**
 * Created by david on 6/10/2015.
 */
public class PacTraDAO {

    public static final String TBL_NAME="pactra";
    public static final String C_PAC="paciente";
    public static final String C_TRA="tratamiento";



    SQLiteDatabase db;

    public PacTraDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertPacTra(Paciente p,Tratamiento t){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getId());
        cV.put(C_TRA, p.getId());


        long id = db.insert(TBL_NAME, null, cV);
        p.setId(id);
    }

    public void updatePaciente(Paciente p,Tratamiento t){
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p.getId());
        cV.put(C_TRA, t.getId());


        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + p.getId()});
    }

    public void deletePaciente(long id){
        db.delete(TBL_NAME, "_id=?", new String[]{"" + id});
    }

    public List<PacTra> getAllPaciente(){
        String sql = "SELECT * FROM "+TBL_NAME;
        return  cursorToList(sql);
    }

    private List<PacTra> cursorToList(String sql){
        List<PacTra> data =  new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        /*
        for(int i=0; i<cursor.getCount();i++){
            cursor.moveToPosition(i);

            PacTra p = new PacTra();
            p.setId(cursor.getLong(0));
            p.setPaciente(cursor.getInt(1));
            p.setTratamiento(cursor.getInt(2));


            data.add(p);
        }
*/
        return data;
    }

}
