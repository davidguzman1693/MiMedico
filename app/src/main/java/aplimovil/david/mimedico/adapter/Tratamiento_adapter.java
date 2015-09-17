package aplimovil.david.mimedico.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import aplimovil.david.mimedico.R;
import aplimovil.david.mimedico.models.Tratamiento;

/**
 * Created by david on 17/09/2015.
 */
public class Tratamiento_adapter extends BaseAdapter{
    Context context;
    List<Tratamiento> data;

    public Tratamiento_adapter(Context context, List<Tratamiento> data) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
       return null;
    }



}
