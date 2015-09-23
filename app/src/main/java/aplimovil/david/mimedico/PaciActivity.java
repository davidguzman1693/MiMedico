package aplimovil.david.mimedico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import aplimovil.david.mimedico.adapter.Paciente_Adapter;
import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class PaciActivity extends AppCompatActivity {

    ListView list;
    Paciente_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paci);

        list = (ListView) findViewById(R.id.list_pa);


        AppUtil.data1 = new ArrayList<>();
        adapter = new Paciente_Adapter(this,AppUtil.data1);
        list.setAdapter(adapter);

        registerForContextMenu(list);
        loadData();
    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    private void loadData() {
        String pacientes[] = getResources().getStringArray(R.array.pacientes);

        for(int i=0; i<pacientes.length;i++){
            Paciente p = new Paciente(pacientes[i]);
            AppUtil.data1.add(p);
        }
        adapter.notifyDataSetChanged();
    }

}
