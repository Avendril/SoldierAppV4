package com.example.nexer.soldierappv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

    @Nullable public static final String User_Name = "userName";
    @Nullable public static final String User_Surname = "userSurname";
    @Nullable public static final String User_ID = "userID";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Preview Soldiers");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        listViewSoldiers = getView().findViewById(R.id.listViewSoldiers);

        userList = new ArrayList<>();
        assert listViewSoldiers != null;
        if (listViewSoldiers != null) {
            listViewSoldiers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    Users user = userList.get(i);
                    Intent intent = new Intent(getActivity(),addOfficers.class);
                    intent.putExtra(User_ID, user.getUserID());
                    intent.putExtra(User_Name, user.getUserName());
                    intent.putExtra(User_Surname, user.getUserSurname());

                    startActivity(intent);
                }
            });
        }
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

                assert listViewSoldiers != null;
                listViewSoldiers.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}