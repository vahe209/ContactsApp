package com.example.contactsapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AddContactDialog extends AppCompatDialogFragment {
    DialogListener listener;
    EditText editTextName,
    editTextPhone;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       LayoutInflater inflater = getActivity().getLayoutInflater();
       View view = inflater.inflate(R.layout.dialog, null);

       editTextName = view.findViewById(R.id.DialogEditName);
       editTextPhone = view.findViewById(R.id.DialogEditPhone);
       builder.setView(view)
               .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       String name = editTextName.getText().toString();
                       String phone = editTextPhone.getText().toString();
                       listener.applyContact(name, phone);

                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               });
        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement DialogListener");
        }

    }

    public interface DialogListener{
        void applyContact(String name, String phone);
    }
}

