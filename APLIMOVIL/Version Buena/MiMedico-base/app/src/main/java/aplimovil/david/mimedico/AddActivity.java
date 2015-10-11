package aplimovil.david.mimedico;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.adapter.Paciente_Adapter;
import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.database.MedPacDAO;
import aplimovil.david.mimedico.database.PacienteDAO;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.util.AppUtil;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String KEY_POS = "pos";
    /*Button btn;
    TextView nompa,cedpa,mailpa;
    int pos = -1;
    */
    SharedPreferences preferences;
    Paciente_Adapter adapter;
    SharedPreferences.Editor editor;
    Paciente paciente;
    PacienteDAO dao;
    MedPacDAO dao1;
    List<Paciente> data;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        list = (ListView) findViewById(R.id.list_paci);

        dao = new PacienteDAO(this);
        dao1 = new MedPacDAO(this);

        data = new ArrayList<>();

        adapter = new Paciente_Adapter(this,data);
        list.setAdapter(adapter);

        registerForContextMenu(list);

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        loadData();
        list.setOnItemClickListener(this);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
/*
    private void loadData() {
        String pacientes[] = getResources().getStringArray(R.array.pacientes_completo);

        for(int i=0; i<pacientes.length;i++){
            String paciente[] = pacientes[i].split(",");
            Paciente p = new Paciente();
            p.setPaciente(paciente[0]);
            p.setCedula(paciente[1]);
            p.setCorreo(paciente[2]);
            AppUtil.data1.add(p);
        }
        adapter.notifyDataSetChanged();
    }*/

    private void loadData(){
        //AppUtil.data1 = Paciente.listarPaci();
        List<Paciente> data1 = new ArrayList<>();
        data1 = dao.getAllPaciente(this,preferences.getLong(LoginActivity.KEY_ID, 0));
        data.addAll(data1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        dao1.insertMedPac(data.get(i).getId(),preferences.getLong(LoginActivity.KEY_ID, 0));
        data.remove(i);
        adapter.notifyDataSetChanged();
    }
}
