package aplimovil.david.mimedico;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import aplimovil.david.mimedico.adapter.Tratamiento_adapter;
import aplimovil.david.mimedico.models.Tratamiento;
import aplimovil.david.mimedico.util.AppUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ima1;
    ImageView ima2;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_medico);
        AppUtil.data2 = new ArrayList<>();
        ima1 = (ImageView) findViewById(R.id.img);
        ima2 = (ImageView) findViewById(R.id.img1);
        ima1.setOnClickListener(this);
        ima2.setOnClickListener(this);
        preferences = getSharedPreferences(LoginActivity.PREFERENCE, MODE_PRIVATE);
        editor = preferences.edit();

    }


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

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

   @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.img:
               Intent intent1 = new Intent(this,PaciActivity.class);
               startActivity(intent1);

               break;
           case R.id.img1:
               Intent intent = new Intent(this,TrataActivity.class);
               startActivity(intent);
               break;

       }
    }
}
