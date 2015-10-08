package aplimovil.david.mimedico.models;

import android.content.Context;
import android.util.Log;

import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by david on 17/09/2015.
 */
public class Tratamiento{
    String tratamiento,finaltratamiento,fechainicio,fechafin,horario,condicion,control;
    Paciente paciente;
    long id;

    public Tratamiento(String tratamiento, String finaltratamiento, String fechainicio, String fechafin, String horario, String condicion, String control) {
        this.tratamiento = tratamiento;
        this.finaltratamiento = finaltratamiento;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.horario = horario;
        this.condicion = condicion;
        this.control = control;

    }

    public Tratamiento() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getFinaltratamiento() {
        return finaltratamiento;
    }

    public void setFinaltratamiento(String finaltratamiento) {
        this.finaltratamiento = finaltratamiento;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    //region A base de datos
    /*
    public static void ingresarTrata(Context context,Tratamiento t){
        SugarContext.init(context);
        if(count(Tratamiento.class)==0) {
            t.save();
        }
        listarTrata();
    }

    public static void listarTrata(){
        List<Tratamiento> data = findWithQuery(Tratamiento.class
                , "SELECT * FROM Tratamiento", null);

        for(int i = 0; i<data.size();i++){
            Log.i("Planeta", "-----------------");
            Log.i("Planeta","ID:"+data.get(i).getId());
            Log.i("Planeta","NOMBRE:"+data.get(i).getTratamiento());
            Log.i("Planeta","GRAVEDAD:"+data.get(i).getControl());
            Log.i("Planeta","TAMANIO"+data.get(i).getFechafin());
        }

    }
    */
    //endregion
}
