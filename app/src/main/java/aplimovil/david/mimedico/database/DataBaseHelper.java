package aplimovil.david.mimedico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;

/**
 * Created by david on 6/10/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    static final String DB_NAME="mimedico.db";
    static int version = 1;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE "+ TratamientoDAO.TBL_NAME
                +" (_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                +TratamientoDAO.C_TRATA+" TEXT,"
                +TratamientoDAO.C_FINALTRA+" TEXT,"
                +TratamientoDAO.C_COND+" TEXT,"
                +TratamientoDAO.C_FINICIO+" TEXT,"
                +TratamientoDAO.C_FFIN+" TEXT,"
                +TratamientoDAO.C_HORA+" TEXT,"
                +TratamientoDAO.C_CONTR+" TEXT"
                +")";

        db.execSQL(sql);

        String sql1 ="CREATE TABLE "+ PacienteDAO.TBL_NAME
                +" (_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                +PacienteDAO.C_PAC+" TEXT,"
                +PacienteDAO.C_CED+" TEXT,"
                +PacienteDAO.C_MAIL+" TEXT"
                +")";

        db.execSQL(sql1);

        String sql2 ="CREATE TABLE "+ PacTraDAO.TBL_NAME
                +" (_id INTEGER AUTO_INCREMENT PRIMARY KEY,"
                +PacTraDAO.C_PAC+" INTEGER REFERENCES "+TratamientoDAO.TBL_NAME+"(_id),"
                +PacTraDAO.C_TRA+" INTEGER REFERENCES "+PacienteDAO.TBL_NAME+"(_id)"
                +")";

        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TratamientoDAO.TBL_NAME);
        db.execSQL("DROP TABLE "+PacienteDAO.TBL_NAME);
        onCreate(db);
    }
}
