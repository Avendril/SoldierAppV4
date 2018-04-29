package com.example.nexer.soldierappv4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class delete extends Fragment {

    Spinner spinner;
    @Nullable EditText editTextName,editTextSurname;
    Button confirm;

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmet_delete_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("users");
//
//        editTextName = (EditText)getView().findViewById(R.id.editTextName);
//        editTextSurname = (EditText)getView().findViewById(R.id.editTextSurname);
//
//        spinner = (Spinner)getView().findViewById(R.id.spinner);
//        confirm = (Button)getView().findViewById(R.id.confirm);
//
//        getActivity().setTitle("delete menu");

//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteUser();
//            }
//        });
    }

    public void deleteUser(){
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();

        boolean fieldsOK = validate(new EditText[] {editTextName,editTextSurname});

        if(fieldsOK){


        }else{
            Toast.makeText(getActivity(),"Please fill in all the fields!",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(EditText[] fields){
        for (EditText currentField : fields) {
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message ,Toast.LENGTH_SHORT).show();
    }
}