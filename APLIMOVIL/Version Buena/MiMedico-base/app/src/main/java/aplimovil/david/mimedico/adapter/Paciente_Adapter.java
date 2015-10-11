package aplimovil.david.mimedico.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aplimovil.david.mimedico.R;
import aplimovil.david.mimedico.models.Paciente;

/**
 * Created by david on 21/09/2015.
 */
public class Paciente_Adapter extends BaseAdapter{
    Context context;
    List<Paciente> data;

    public Paciente_Adapter(Context context, List<Paciente> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = null;

        if (convertView == null){

            v = View.inflate(context, R.layout.template_pacientes,null);
        }else{
            v = convertView;
        }

        Paciente p = (Paciente) getItem(i);
        TextView txt = (TextView) v.findViewById(R.id.nombrePa);
        txt.setText(p.getPaciente());

        txt = (TextView) v.findViewById(R.id.txt_ced);


        txt = (TextView) v.findViewById(R.id.txt_correo);
        txt.setText(p.getCorreo());

        return v;
    }
}
