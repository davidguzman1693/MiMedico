package aplimovil.david.mimedico;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import aplimovil.david.mimedico.database.TratamientoDAO;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class AddTraActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_POS = "pos";
    Button btn;
    TextView nomtra,fin,cond,fechaini,fechafin,hora,control;
    int pos = -1;
    Tratamiento tratami;
    TratamientoDAO dao;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tra);
        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();

        dao = new TratamientoDAO(this);

        btn = (Button) findViewById(R.id.btn_add_trata);

        nomtra = (TextView) findViewById(R.id.txt_nom_tra);
        fin = (TextView) findViewById(R.id.txt_fin_tra);
        cond = (TextView) findViewById(R.id.txt_condi_trata);
        fechaini = (TextView) findViewById(R.id.txt_fechaini_tra);
        fechafin = (TextView) findViewById(R.id.txt_fechafin_tra);
        hora = (TextView) findViewById(R.id.txt_hor_trata);
        control = (TextView) findViewById(R.id.txt_control_trata);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {

            pos = extras.getInt(KEY_POS);
            tratami = AppUtil.data.get(pos);

            nomtra.setText(tratami.getTratamiento());
            fin.setText(tratami.getFinaltratamiento());
            cond.setText(tratami.getCondicion());
            fechaini.setText(tratami.getFechainicio());
            fechafin.setText(tratami.getFechafin());
            hora.setText(tratami.getHorario());
            control.setText(tratami.getControl());

            btn.setText("Editar");
        }
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String trata = nomtra.getText().toString();
        String fina = fin.getText().toString();
        String condi = cond.getText().toString();
        String fini = fechaini.getText().toString();
        String ffin = fechafin.getText().toString();
        String hor = hora.getText().toString();
        String cont = control.getText().toString();

        if(pos==-1) {
            Tratamiento p = new Tratamiento(trata,fina,fini,ffin,hor,condi,cont);
            //AppUtil.data.add(p);
            //Tratamiento.ingresarTrata(this,p);
            dao.insertTratamiento(p,preferences.getLong(LoginActivity.KEY_ID, 0));
            AppUtil.data.add(p);
        }else{

            tratami.setTratamiento(trata);
            tratami.setFinaltratamiento(fina);
            tratami.setCondicion(condi);
            tratami.setFechainicio(fini);
            tratami.setFechafin(ffin);
            tratami.setHorario(hor);
            tratami.setControl(cont);
            dao.updateTratamiento(tratami);
            AppUtil.data = new ArrayList<>();
            AppUtil.data.addAll(dao.getAllTratamiento1(preferences.getLong(LoginActivity.KEY_ID, 0)));
        }

        finish();


    }
}
