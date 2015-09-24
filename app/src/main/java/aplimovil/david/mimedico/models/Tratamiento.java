package aplimovil.david.mimedico.models;

/**
 * Created by david on 17/09/2015.
 */
public class Tratamiento {
    String tratamiento,finaltratamiento,fechainicio,fechafin,horario,condicion,control;
    Paciente paciente;

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
}
