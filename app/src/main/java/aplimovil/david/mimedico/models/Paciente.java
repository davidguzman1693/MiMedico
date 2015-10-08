package aplimovil.david.mimedico.models;

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by david on 21/09/2015.
 */
public class Paciente{
    String paciente,cedula,correo;
    long id;

    public Paciente(String paciente, String cedula, String correo) {
        this.paciente = paciente;
        this.cedula = cedula;
        this.correo = correo;
    }
    public Paciente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }



    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }



    //region A base de datos
    /*
    public static List<Paciente> ingresarPaci(Context context,Paciente p){
        SugarContext.init(context);
        if(count(Paciente.class)==0) {
            p.save();
        }
        return listarPaci();
    }

    public static List<Paciente> listarPaci(){
        List<Paciente> data1 = findWithQuery(Paciente.class
                , "SELECT * FROM Paciente", null);
        return  data1;
    }*/
    //endregion
}
