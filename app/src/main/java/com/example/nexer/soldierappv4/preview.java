package com.example.nexer.soldierappv4;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class preview extends Fragment {

    @Nullable ListView listViewSoldiers;

    DatabaseReference databaseReference;
    List<Users> userList;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Preview Soldiers");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        listViewSoldiers = (ListView)getView().findViewById(R.id.listViewSoldiers);

        userList = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               userList.clear();
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    Users users = userSnapshot.getValue(Users.class);
                    userList.add(users);
                }
                UsersList adapter = new UsersList(getActivity(), userList);

                listViewSoldiers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}