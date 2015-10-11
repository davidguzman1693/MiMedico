package aplimovil.david.mimedico;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.adapter.Pac_Tra_Adapter;
import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.database.MedPacDAO;
import aplimovil.david.mimedico.database.PacTraDAO;
import aplimovil.david.mimedico.database.TratamientoDAO;
import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class PacTraActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static final String KEY_POS = "pos";
    ListView list;
    Tratamiento_adapter adapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    List<Tratamiento> tra;
    Paciente paciente;
    TratamientoDAO dao;
    PacTraDAO dao1;
    int pos=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pac_tra);

        list = (ListView) findViewById(R.id.list_tm);


        tra = new ArrayList<>();
        adapter = new Tratamiento_adapter(this,tra);
        list.setAdapter(adapter);

        registerForContextMenu(list);

        dao = new TratamientoDAO(this);
        dao1 = new PacTraDAO(this);

        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        paciente = new Paciente();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            pos = extras.getInt(KEY_POS);
            paciente = AppUtil.data1.get(pos);
        }
        loadData();

        list.setOnItemClickListener(this);

    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    public void loadData(){
        List<Tratamiento> data1 = new ArrayList<>();
        data1 = dao.getAllTratamiento(this, paciente.getId(), preferences.getLong(LoginActivity.KEY_ID, 0));
        tra.addAll(data1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        PacTra p = new PacTra();
        p.setPaciente(paciente.getId());
        p.setTratamiento(tra.get(i).getId());
        //AppUtil.data2.add(p);
        dao1.insertPacTra(p);
        tra.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(this,"Agregado a la lista",Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,"Agregado:"+paciente.getPaciente()+"Trata: "+AppUtil.data.get(i).getTratamiento(),Toast.LENGTH_SHORT).show();
    }
}
