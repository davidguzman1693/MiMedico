package aplimovil.david.mimedico.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import aplimovil.david.mimedico.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SingupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SingupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingupFragment extends Fragment implements View.OnClickListener{
    EditText nom, mail,usr,pass;
    RadioGroup perfil;
    Button bn;
    String per="Hola";
    public SingupFragment() {
        // Required empty public constructor
    }


    //Interfaces

    public interface onRegister{
        void onRegister(String nom, String mail, String usr, String perfil, String pass);
    }
    onRegister onRegister;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onRegister = (onRegister) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_singup, container, false);
        nom = (EditText) v.findViewById(R.id.nombre);
        mail = (EditText) v.findViewById(R.id.mail);
        perfil = (RadioGroup) v.findViewById(R.id.perfil);
        usr = (EditText) v.findViewById(R.id.usrre);
        pass = (EditText) v.findViewById(R.id.passre);
        bn = (Button) v.findViewById(R.id.btn_reg);
        bn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (perfil.getCheckedRadioButtonId()){
            case R.id.paciente:
                onRegister.onRegister(nom.getText().toString(),mail.getText().toString(),usr.getText().toString(),"Paciente",pass.getText().toString());
                break;
            case R.id.medico:
                onRegister.onRegister(nom.getText().toString(),mail.getText().toString(),usr.getText().toString(),"Medico",pass.getText().toString());
                break;
        }
        //onRegister.onRegister(nom.getText().toString(),mail.getText().toString(),usr.getText().toString(),per,pass.getText().toString());
    }
}
