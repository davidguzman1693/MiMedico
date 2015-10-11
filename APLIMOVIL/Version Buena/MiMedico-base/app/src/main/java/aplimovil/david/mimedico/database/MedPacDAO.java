package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.models.MedPac;
import aplimovil.david.mimedico.models.Medico;
import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;

/**
 * Created by david on 10/10/2015.
 */
public class MedPacDAO {
    public static final String TBL_NAME="madpac";
    public static final String C_PAC="paciente";
    public static final String C_MED="medico";



    SQLiteDatabase db;

    public MedPacDAO(Context context){
        DataBaseHelper helper =  new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insertMedPac(Long p,Long m){
        Log.i("MedPac","MadPac Insertado");
        ContentValues cV = new ContentValues();
        cV.put(C_PAC, p);
        cV.put(C_MED, m);


        long id = db.insert(TBL_NAME, null, cV);
    }

    public void updateMedPac(Paciente p,Medico m){
        ContentValues cV = new ContentValues();

        cV.put(C_PAC, p.getId());
        cV.put(C_MED, m.getId());


        db.update(TBL_NAME, cV, "_id=?", new String[]{"" + p.getId()});
    }

    public boolean deleteMedPac(long p,long m){
        MedPac med = new MedPac();
        String sql = "SELECT * FROM "+TBL_NAME +" WHERE "+C_MED+" = '"+m+"'";
        med = cursorToList1(sql,p,m);
        if(med.getId()!=0) {
            db.delete(TBL_NAME, "_id=?", new String[]{"" + med.getId()});
            Log.i("Información", "Borró MEDPAC "+med.getId());
            return true;
        }
        else{
            Log.i("Información", "No lo borró");
            return false;
        }
    }

    public List<MedPac> getAllMedPac(long id){
        String sql = "SELECT * FROM "+TBL_NAME +" WHERE "+C_MED+" = '"+id+"'";
        return  cursorToList(sql);
    }

    private List<MedPac> cursorToList(String sql) {
        List<MedPac> data = new ArrayList<>();

            Cursor cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
               int i= 0;
                do{
                    i=i+1;
                    cursor.moveToPosition(i);

                    MedPac p = new MedPac();
                    p.setId(cursor.getLong(0));
                    p.setPaciente(cursor.getLong(1));
                    p.setMedico(cursor.getLong(2));
                    Log.i("IDMedPac", "" + p.getId());
                    Log.i("IDPaciente",""+p.getPaciente());
                    Log.i("IDMedico",""+p.getMedico());

                    data.add(p);
                }while (cursor.moveToNext());
            }
            return data;

    }
    private MedPac cursorToList1(String sql,long pac, long med) {
        MedPac p = new MedPac();
        p.setId(0);
        try {
            Cursor cursor = db.rawQuery(sql, null);

            for (int i = 0; i < cursor.getCount(); i++) {

                cursor.moveToPosition(i);

                if(cursor.getLong(1)==pac && cursor.getLong(2)==med){
                    p.setId(cursor.getLong(0));
                    p.setPaciente(cursor.getLong(1));
                    p.setMedico(cursor.getLong(2));
                    return p;
                }
            }
            return p;

        }catch (NullPointerException e){
            return p;
        }

    }

}
