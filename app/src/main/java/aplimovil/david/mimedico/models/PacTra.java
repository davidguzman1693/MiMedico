package aplimovil.david.mimedico.models;

/**
 * Created by david on 30/09/2015.
 */
public class PacTra {
    Paciente paciente;
    Tratamiento tratamiento;
    long id;
    public PacTra() {
    }

    public PacTra(Paciente paciente, Tratamiento tratamiento) {
        this.paciente = paciente;
        this.tratamiento = tratamiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
}
