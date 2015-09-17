package aplimovil.david.mimedico;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_medico);

        ima1 = (ImageView) findViewById(R.id.img);
        ima2 = (ImageView) findViewById(R.id.img1);
        ima1.setOnClickListener(this);
        ima2.setOnClickListener(this);


    }




   @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.img:
               Toast.makeText(this, "Informacion del paciente", Toast.LENGTH_SHORT).show();
               break;
           case R.id.img1:
               Intent intent1 = new Intent(this,TrataActivity.class);

               break;

       }
    }
}
