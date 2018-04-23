package com.example.nexer.soldierappv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profile extends Fragment {

    private FirebaseAuth firebaseAuth2;
    private DatabaseReference databaseReference;

    private Button Logout,Confirm;
    private EditText editTextName,editTextAddress,editTextAge;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        firebaseAuth2 = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Button Logout = (Button)getView().findViewById(R.id.logoutbutton);

        Logout.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
               Toast.makeText(getActivity(), "Logout successful!",Toast.LENGTH_SHORT).show();
               firebaseAuth2.signOut();
                getActivity().startActivity(new Intent(getContext(),LoginActivity.class));
            }
        });

    }
}