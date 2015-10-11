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
import aplimovil.david.mimedico.database.TratamientoDAO;
import aplimovil.david.mimedico.models.PacTra;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class Main2Activity extends AppCompatActivity {

    Tratamiento_adapter adapter;
    ListView list;
    int pos;
    List<Tratamiento> tra;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TratamientoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        list = (ListView) findViewById(R.id.list_tm1);

        tra = new ArrayList<>();

        adapter = new Tratamiento_adapter(this,tra);
        list.setAdapter(adapter);

        registerForContextMenu(list);
        dao = new TratamientoDAO(this);
        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();
        loadData();
    }

    private void loadData() {
        List<Tratamiento> data1= new ArrayList<>();
        data1= dao.getAllTratamiento2(this,preferences.getLong(LoginActivity.KEY_ID, 0));
        tra.addAll(data1);
        adapter.notifyDataSetChanged();
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
