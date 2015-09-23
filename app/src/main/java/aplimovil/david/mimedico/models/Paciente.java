package aplimovil.david.mimedico.models;

/**
 * Created by david on 21/09/2015.
 */
public class Paciente {
    String paciente,cedula,correo;

    public Paciente(String paciente, String cedula, String correo) {
        this.paciente = paciente;
        this.cedula = cedula;
        this.correo = correo;
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


    public Paciente() {
    }

}
