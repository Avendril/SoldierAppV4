package com.example.nexer.soldierappv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class profile extends Fragment {

    private Button Logout;
    private FirebaseAuth firebaseAuth;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logout = (Button)getView().findViewById(R.id.logoutbutton);
        return inflater.inflate(R.layout.fragment_profile_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles  logoutbutton
        getActivity().setTitle("profile menu");


    }

    public void onClick(View view){
        if(view == Logout){
            firebaseAuth.signOut();
        }else{

        }
    }


}