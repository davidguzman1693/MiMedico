package aplimovil.david.mimedico.models;

/**
 * Created by david on 10/10/2015.
 */

    public class Medico{
        String medico,correo,usuario,password;

        long id;


    public Medico(String medico, String correo, String usuario, String password) {
        this.medico = medico;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }



        public Medico() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }



        public String getMedico() {
            return medico;
        }

        public void setMedico(String medico) {
            this.medico = medico;
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

