package aplimovil.david.mimedico.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import aplimovil.david.mimedico.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText usr;
    EditText pass;
    Button in,sin;



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_log:
                onClick.onClick(usr.getText().toString(), pass.getText().toString());
                break;
            case R.id.singup:
                Log.i("Aja", "Entra");
                onSingup.OnSingup();
                break;
        }

    }

   public interface onClick{
        void onClick(String usr, String pass);
    }

    public interface onSingup{
        void OnSingup();
    }
    onSingup onSingup;
    onClick onClick;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onClick = (onClick) context;
        onSingup = (onSingup) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        usr = (EditText) v.findViewById(R.id.usr);
        pass = (EditText)v. findViewById(R.id.pass);
        in = (Button) v.findViewById(R.id.btn_log);
        sin = (Button) v.findViewById(R.id.singup);

        in.setOnClickListener(this);
        sin.setOnClickListener(this);

        return v;
    }


}
