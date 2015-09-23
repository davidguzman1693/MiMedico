package aplimovil.david.mimedico.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import aplimovil.david.mimedico.R;
import aplimovil.david.mimedico.TrataActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MasterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MasterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterFragment extends Fragment{



   public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.f_master, container, false);

        return v;
    }


}
