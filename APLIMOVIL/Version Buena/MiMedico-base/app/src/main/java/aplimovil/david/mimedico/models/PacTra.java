package aplimovil.david.mimedico.models;

/**
 * Created by david on 30/09/2015.
 */
public class PacTra {
    long paciente;
    long tratamiento;
    long id;
    public PacTra() {
    }

    public PacTra(long paciente, long tratamiento) {
        this.paciente = paciente;
        this.tratamiento = tratamiento;
    }

    public long getPaciente() {
        return paciente;
    }

    public void setPaciente(long paciente) {
        this.paciente = paciente;
    }

    public long getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(long tratamiento) {
        this.tratamiento = tratamiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
