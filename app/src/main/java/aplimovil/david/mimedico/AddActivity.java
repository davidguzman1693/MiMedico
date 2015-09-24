package aplimovil.david.mimedico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.util.AppUtil;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_POS = "pos";
    Button btn;
    TextView nompa,cedpa,mailpa;
    int pos = -1;
    Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn = (Button) findViewById(R.id.btn_add);
        nompa = (TextView) findViewById(R.id.txt_nom_pa);
        cedpa = (TextView) findViewById(R.id.txt_ced_pa);
        mailpa = (TextView) findViewById(R.id.txt_mail_pa);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            pos = extras.getInt(KEY_POS);
            getSupportActionBar().setTitle("Editar Paciente");
            paciente = AppUtil.data1.get(pos);
            nompa.setText(paciente.getPaciente());
            cedpa.setText(paciente.getCedula());
            mailpa.setText(paciente.getCorreo());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nom = nompa.getText().toString();
        String ced = cedpa.getText().toString();
        String mail= mailpa.getText().toString();
        if(pos==-1) {
            Paciente p = new Paciente(nom, ced, mail);
            AppUtil.data1.add(p);
        }else{
            paciente.setPaciente(nom);
            paciente.setCedula(ced);
            paciente.setCorreo(mail);
        }

        finish();

    }
}
