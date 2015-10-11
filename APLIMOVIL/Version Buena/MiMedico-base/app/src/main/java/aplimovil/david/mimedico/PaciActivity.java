package aplimovil.david.mimedico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.adapter.Paciente_Adapter;
import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.database.MedPacDAO;
import aplimovil.david.mimedico.database.MedicoDAO;
import aplimovil.david.mimedico.database.PacienteDAO;
import aplimovil.david.mimedico.models.Paciente;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class PaciActivity extends AppCompatActivity {

    ListView list;
    Paciente_Adapter adapter;
    int pos;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    MedPacDAO dao1;
    PacienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paci);

        list = (ListView) findViewById(R.id.list_pa);

        dao = new PacienteDAO(this);

        AppUtil.data1 = new ArrayList<>();
        adapter = new Paciente_Adapter(this,AppUtil.data1);
        list.setAdapter(adapter);

        registerForContextMenu(list);

        dao1 = new MedPacDAO(this);
        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();

        loadData();

    }

    @Override
    protected void onRestart() {
        AppUtil.data1 = new ArrayList<>();
        loadData();
        adapter.notifyDataSetChanged();
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
        Log.i("Hola",""+preferences.getLong(LoginActivity.KEY_ID, 0));
        List<Paciente> data1 = new ArrayList<>();
        data1 = dao.getAllPaciente1(this, preferences.getLong(LoginActivity.KEY_ID, 0));
        AppUtil.data1.addAll(data1);
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

            case R.id.action_logout:
                editor.putBoolean(LoginActivity.KEY_LOGIN, false);
                editor.commit();

                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
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
            case R.id.action_delete:
                boolean res;
                res = dao1.deleteMedPac(AppUtil.data1.get(pos).getId(), preferences.getLong(LoginActivity.KEY_ID, 0));

                if(res==true){
                    AppUtil.data1.remove(pos);
                    Toast.makeText(this,"Registro eliminado",Toast.LENGTH_SHORT);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(this,"Error en la conexión",Toast.LENGTH_SHORT);
                }

                break;
            case R.id.action_add_pac_tra:
                Intent intent = new Intent(this,PacTraActivity.class);
                intent.putExtra(AddActivity.KEY_POS, pos);
                startActivity(intent);
                break;


        }
        return super.onContextItemSelected(item);
    }
    //endregion



}
