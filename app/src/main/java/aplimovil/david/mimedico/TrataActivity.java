package aplimovil.david.mimedico;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_tratamientos);
        /*list = (ListView) findViewById(R.id.list_tm);


        AppUtil.data = new ArrayList<>();
        adapter = new Tratamiento_adapter(this,AppUtil.data);
        list.setAdapter(adapter);

        registerForContextMenu(list);
        loadData();*/

    }


    @Override
    protected void onRestart() {
        //adapter.notifyDataSetChanged();
        super.onRestart();
    }
/*
    private void loadData(){
        String tratamiento[] = getResources().getStringArray(R.array.tratas);

        for(int i=0; i<tratamiento.length;i++){
            Tratamiento p = new Tratamiento();
            AppUtil.data.add(p);
        }
        adapter.notifyDataSetChanged();
    }
*/
}
