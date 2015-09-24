package aplimovil.david.mimedico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    int pos;

    AlertDialog delete;

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
    }

    //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,AddActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region ContextMenu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        pos = info.position;

        switch (item.getItemId()){
            case R.id.acction_edit:
                Intent intent = new Intent(this,AddActivity.class);
                intent.putExtra(AddActivity.KEY_POS,pos);
                startActivity(intent);
                break;
            case R.id.action_delete:
                AppUtil.data1.remove(pos);
                adapter.notifyDataSetChanged();
                break;


        }
        return super.onContextItemSelected(item);
    }
    //endregion



}
