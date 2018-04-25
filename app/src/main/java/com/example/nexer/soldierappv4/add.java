package com.example.nexer.soldierappv4;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends Fragment {

    EditText editTextName;
    Button buttonAddUser;
    Spinner spinnerGender;

    DatabaseReference mDBReference;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add User");

        mDBReference = FirebaseDatabase.getInstance().getReference("users");

        editTextName = (EditText)getView().findViewById(R.id.editTextName);
        buttonAddUser = (Button)getView().findViewById(R.id.buttonAddUsers);
        spinnerGender = (Spinner)getView().findViewById(R.id.spinnerGender);

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        String name = editTextName.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
           String id = mDBReference.push().getKey();
           Users users = new Users(id, name, gender);
           mDBReference.child(id).setValue(users);

           Toast.makeText(getActivity(),"User added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Please enter your name",Toast.LENGTH_SHORT).show();
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message ,Toast.LENGTH_SHORT).show();
    }
}