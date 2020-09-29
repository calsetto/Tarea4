package com.example.tarea4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class Edit_info extends AppCompatDialogFragment {
    private EditText enombre;
    private EditText emateria;
    private EditText einstitucion;
    private InfoListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.edit, null);

        builder.setView(v)
                .setTitle("Informacion")
                .setCancelable(false)
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = enombre.getText().toString();
                        String subject = emateria.getText().toString();
                        String institution = einstitucion.getText().toString();

                        if(name.isEmpty() || subject.isEmpty() || institution.isEmpty()){

                            Toast.makeText(getActivity(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();

                        }else{

                            listener.applytext(name, subject, institution);
                        }
                    }
                });
        enombre = v.findViewById(R.id.Enombre);
        emateria = v.findViewById(R.id.Emateria);
        einstitucion = v.findViewById(R.id.Einstitucion);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (InfoListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "kkk");
        }
    }

    public interface InfoListener{
        void applytext(String value1, String value2, String value3);
    }
}
