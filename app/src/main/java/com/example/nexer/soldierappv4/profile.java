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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends Fragment {

    EditText editTextName,editTextSurname,editTextAddress,editTextNationality;
    Button buttonAddUser;
    Spinner spinnerGender;

    DatabaseReference mDBReference;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Soldier");

        mDBReference = FirebaseDatabase.getInstance().getReference("users");

        editTextName = getView().findViewById(R.id.editTextName);
        editTextSurname = getView().findViewById(R.id.editTextSurname);
        editTextAddress = getView().findViewById(R.id.editTextAddress);
        editTextNationality = getView().findViewById(R.id.editTextNationality);

        buttonAddUser = getView().findViewById(R.id.buttonAddUsers);
        spinnerGender = getView().findViewById(R.id.spinnerGender);

        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser(){
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String nationality = editTextNationality.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();

        boolean fieldsOK = validate(new EditText[] {editTextName,editTextSurname,editTextAddress,editTextNationality});

        if(fieldsOK){
            String id = mDBReference.push().getKey();
            Users users = new Users(id, name, surname, address, nationality, gender);
            //String fusion = name;
            mDBReference.child(id).setValue(users);
            Toast.makeText(getActivity(),"Soldier added",Toast.LENGTH_SHORT).show();

//           if(!mDBReference.child("userName").equals(name)){
//               mDBReference.child(id).setValue(users);
//               Toast.makeText(getActivity(),"Soldier added",Toast.LENGTH_SHORT).show();
//           }else{
//               Toast.makeText(getActivity(),"Soldier already exists!",Toast.LENGTH_SHORT).show();
//           }

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