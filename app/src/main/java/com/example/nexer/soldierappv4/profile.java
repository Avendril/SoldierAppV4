package com.example.nexer.soldierappv4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class profile extends Fragment {

    void Start() {
        // Set up the Editor before calling into the realtime database.
        FirebaseApp.DefaultInstance.SetEditorDatabaseUrl("https:///androidstudioapp-e04be.firebaseio.com/");

        // Get the root reference location of the database.
        DatabaseReference reference = FirebaseDatabase.DefaultInstance.RootReference;
    }
    List<UserInfo> userInfoList;

    private FirebaseAuth firebaseAuth2;
    private DatabaseReference databaseReference;

    private Button Save;
    private EditText editTextName,editTextSurname,editTextAge,editTextEmail;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        firebaseAuth2 = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = firebaseAuth2.getCurrentUser();
        databaseReference.child(user.getUid());

        userInfoList = new ArrayList<>();
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                editTextName.setText(user.Name());
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        Save = (Button)getView().findViewById(R.id.Save);
        editTextName = (EditText)getView().findViewById(R.id.editTextName);
        editTextSurname = (EditText)getView().findViewById(R.id.editTextSurname);
        editTextAge = (EditText)getView().findViewById(R.id.editTextAge);
        editTextEmail = (EditText)getView().findViewById(R.id.editTextEmail);

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                userInfoList.clear();
//                for(DataSnapshot userInfo : dataSnapshot.getChildren()){
//                    UserInfo users = userInfo.getValue(UserInfo.class);
//                    userInfoList.add(users);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        if(user != null){

            editTextName.setText(user.getDisplayName());
//            editTextSurname.setText(user.Surname);
//            editTextAge.setText(user.Age);
            editTextEmail.setText(user.getEmail());
        }

        Save.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
              String name = editTextName.getText().toString().trim();
              String surname = editTextSurname.getText().toString().trim();
              String age = editTextAge.getText().toString().trim();
              String email = editTextEmail.getText().toString().trim();
              userInfo userInfo = new userInfo(name,surname,age,email);

              FirebaseUser user = firebaseAuth2.getCurrentUser();
              databaseReference.child(user.getUid()).setValue(userInfo);

              Toast.makeText(getActivity(), "Data saved!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}