package com.example.nexer.soldierappv4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends Fragment {

    private EditText editTextName,editTextSurname,editTextAge,editTextEmail;
    private Button saveButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add Info");

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        saveButton = (Button)getView().findViewById(R.id.Save);
        editTextName = (EditText)getView().findViewById(R.id.editTextName);
        editTextSurname = (EditText)getView().findViewById(R.id.editTextSurname);
        editTextAge = (EditText)getView().findViewById(R.id.editTextAge);
        editTextEmail = (EditText)getView().findViewById(R.id.editTextEmail);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String surname = editTextSurname.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                userInfo userInfo = new userInfo(name,surname,age,email);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(userInfo);

                Toast.makeText(getActivity(), "Data saved!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}