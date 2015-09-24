package aplimovil.david.mimedico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

/**
 * Created by david on 17/09/2015.
 */
public class TrataActivity extends AppCompatActivity {

    Tratamiento_adapter adapter;
    ListView list;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_tratamientos);
        list = (ListView) findViewById(R.id.list_tm);


        AppUtil.data = new ArrayList<>();
        adapter = new Tratamiento_adapter(this,AppUtil.data);
        list.setAdapter(adapter);

        registerForContextMenu(list);
        loadData();


    }


    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

    private void loadData(){
        String tratamientos[] = getResources().getStringArray(R.array.tratas_commpleto);

        for(int i=0; i<tratamientos.length;i++){
            String tratamiento[] = tratamientos[i].split(",");
            Tratamiento t = new Tratamiento();
            t.setTratamiento(tratamiento[0]);
            t.setFinaltratamiento(tratamiento[1]);
            t.setCondicion(tratamiento[2]);
            t.setHorario(tratamiento[3]);
            t.setFechainicio(tratamiento[4]);
            t.setFechafin(tratamiento[5]);
            t.setControl(tratamiento[6]);
            AppUtil.data.add(t);
        }
        adapter.notifyDataSetChanged();
    }

    //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent = new Intent(this,AddTraActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.acction_edit:
                Intent intent = new Intent(this,AddTraActivity.class);
                intent.putExtra(AddTraActivity.KEY_POS,pos);
                startActivity(intent);
                break;
            case R.id.action_delete:
                AppUtil.data.remove(pos);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
