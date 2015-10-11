package aplimovil.david.mimedico.models;

import android.util.Log;

/**
 * Created by david on 10/10/2015.
 */
public class MedPac {
    Long paciente;
    Long medico;
    long id;

    public MedPac() {
    }

    public MedPac(Long paciente, Long medico) {
        this.paciente = paciente;
        this.medico = medico;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public Long getMedico() {
        return medico;
    }

    public void setMedico(Long medico) {
        this.medico = medico;
    }
}
