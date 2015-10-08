package aplimovil.david.mimedico;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import aplimovil.david.mimedico.adapter.Pac_Tra_Adapter;
import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class Main2Activity extends AppCompatActivity {

    Pac_Tra_Adapter adapter;
    ListView list;
    int pos;
    List<PacTra> pactra;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = (ListView) findViewById(R.id.list_tm1);
        pactra = new ArrayList<>();
        for(int i =0; i<AppUtil.data2.size();i++){
            if(AppUtil.data2.get(i).getPaciente().getPaciente().equals("Paciente 1")){
                pactra.add(AppUtil.data2.get(i));
            }
        }
        adapter = new Pac_Tra_Adapter(this,pactra);
        list.setAdapter(adapter);

        registerForContextMenu(list);
        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }

   //region OptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout1:
                editor.putBoolean(LoginActivity.KEY_LOGIN, false);
                editor.commit();

                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

}
