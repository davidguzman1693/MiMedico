package aplimovil.david.mimedico;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aplimovil.david.mimedico.database.MedicoDAO;
import aplimovil.david.mimedico.database.PacienteDAO;
import aplimovil.david.mimedico.fragments.LoginFragment;
import aplimovil.david.mimedico.fragments.SingupFragment;
import aplimovil.david.mimedico.models.Medico;
import aplimovil.david.mimedico.models.Paciente;

public class LoginActivity extends AppCompatActivity implements LoginFragment.onClick,LoginFragment.onSingup,SingupFragment.onRegister{
    public static final String KEY_LOGIN="login";
    public static final String KEY_USER="user";
    public static final String KEY_ID="id";
    public static final String KEY_PERFIL="perfil";


    public static final String PREFERENCE="preference";

    EditText usr, pass;
    Button in;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    LoginFragment login;
    SingupFragment sin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = new LoginFragment();
        sin = new SingupFragment();
        /*
        usr = (EditText) findViewById(R.id.usr);
        pass = (EditText) findViewById(R.id.pass);

        in = (Button) findViewById(R.id.btn_log);

        in.setOnClickListener(this);*/

        preferences = getSharedPreferences(PREFERENCE,MODE_PRIVATE);
        editor =preferences.edit();
        putFragment(R.id.container,login);

    }

    public void putFragment(int idContainer, Fragment fragment){
        FragmentTransaction fT = getSupportFragmentManager().beginTransaction();
        fT.replace(idContainer,fragment);

        fT.commit();
    }


    public void onClick(String usr, String pass) {

        PacienteDAO dao = new PacienteDAO(this);
        Paciente p = new Paciente();

        p = dao.inicioSesion(usr,pass);
        if(p.getId()!=0){
            Log.i("Da", "Entro a validar paciente");
            editor.putBoolean(KEY_LOGIN, true);
            editor.putString(KEY_USER, usr);
            editor.putLong(KEY_ID, p.getId());
            editor.putString(KEY_PERFIL,"Paciente");
            editor.commit();

            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            finish();
            //Toast.makeText(this, "Paciente", Toast.LENGTH_SHORT).show();

        }
       else{
            MedicoDAO dao1 = new MedicoDAO(this);
            Medico m = new Medico();
            m = dao1.inicioSesion(usr,pass);
            if(m.getId()!=0){
                Log.i("Da", "Entro a validar medico");
                editor.putBoolean(KEY_LOGIN, true);
                editor.putString(KEY_USER, usr);
                editor.putLong(KEY_ID, m.getId());
                editor.putString(KEY_PERFIL,"Medico");
                editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


        }
            else {
                Toast.makeText(this, "Usuario o contraseña erronea", Toast.LENGTH_SHORT).show();
            }
        }



    }
    public void OnSingup(){
        putFragment(R.id.container, sin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onRegister(String nom, String mail, String usr, String perfil, String pass){

        if(perfil.equals("Medico")){
            Log.i("Medico","Este es el usr"+usr);
            Log.i("Medico","Esta es la pass: "+pass);
            MedicoDAO dao1 = new MedicoDAO(this);
            Medico m = new Medico(nom,mail,usr,pass);
            Log.i("Medico","Este es el usr insertado"+m.getUsuario());
            Log.i("Medico","Esta es la pass insertado: "+m.getPassword());
            dao1.insertMedico(m);
        }if(perfil.equals("Paciente")){
            Log.i("Hola","Paciente");
        PacienteDAO dao = new PacienteDAO(this);
        Paciente p = new Paciente(nom,mail,usr,pass);

        dao.insertPaciente(p);}

        putFragment(R.id.container,login);
    }
}
