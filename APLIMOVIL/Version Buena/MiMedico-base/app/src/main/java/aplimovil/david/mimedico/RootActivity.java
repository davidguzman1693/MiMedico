package aplimovil.david.mimedico;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by david on 27/09/2015.
 */
public class RootActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);

        boolean login = preferences.getBoolean(LoginActivity.KEY_LOGIN, false);
        String perfil = preferences.getString(LoginActivity.KEY_PERFIL, "");
        Intent intent = null;

        if(login) {
           /* Log.i("Info","Perfil: "+perfil);
            if(perfil=="Medico") {
                Log.i("Info","Valida medico");
                intent = new Intent(this, MainActivity.class);
            }
            else{
                if(perfil=="Paciente"){
                    Log.i("Info","Valida paciente");
                    intent = new Intent(this, Main2Activity.class);
                }
                else {
                    Log.i("Info","No entro");
                    intent = new Intent(this, LoginActivity.class);
                }
            }*/
            intent = new Intent(this, MainActivity.class);
        }
        else {
            Log.i("Info","Ni entro");
            intent = new Intent(this, LoginActivity.class);
        }

        startActivity(intent);
    }
}
