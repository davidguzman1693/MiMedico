package aplimovil.david.mimedico.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aplimovil.david.mimedico.R;
import aplimovil.david.mimedico.models.PacTra;

/**
 * Created by david on 30/09/2015.
 */
public class Pac_Tra_Adapter extends BaseAdapter{
    Context context;
    List<PacTra> data;

    public Pac_Tra_Adapter(Context context, List<PacTra> data) {
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

            v = View.inflate(context, R.layout.template_pac_tra,null);
        }else{
            v = convertView;
        }
        /*
        PacTra p = (PacTra) getItem(i);
        TextView txt = (TextView) v.findViewById(R.id.txt_TrataPac);
        txt.setText(p.getTratamiento().getTratamiento());
        txt = (TextView) v.findViewById(R.id.txt_pacTrata);
        txt.setText(p.getPaciente().getPaciente());
        */
        return v;
    }
}
