package com.example.nexer.soldierappv4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class preview extends Fragment {

    @Nullable ListView listViewSoldiers;
    Users user = new Users();
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

            listViewSoldiers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                    Users user = userList.get(i);
                    showUpdateDialog(user.getUserID(),user.getUserName(),user.getUserSurname(), user.getUserAddress(), user.getUserNationality(), user.getUserGender());
                    return false;
                }
            });
        }
    }

    private void showUpdateDialog(final String soldierID, final String soldierNameString , final String soldierSurname, final String soldierAddress, final String soldierNationality, final String soldierGender){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.updatedialoguedata, null);
        dialogBuilder.setView(dialogView);

        final EditText edittextName = (EditText)dialogView.findViewById(R.id.editTextName);
        final EditText edittextSurname = (EditText)dialogView.findViewById(R.id.editTextSurname);
        final EditText edittextAddress = (EditText)dialogView.findViewById(R.id.editTextAddress);
        final Button confimButton = (Button)dialogView.findViewById(R.id.updateButton);

        confimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edittextName.getText().toString().trim();
                String surname = edittextSurname.getText().toString().trim();
                String address = edittextAddress.getText().toString().trim();

                boolean fieldsOK = validate(new EditText[] {edittextName,edittextSurname,edittextAddress});

                if(fieldsOK){
                    updateUser(soldierID, name, surname, address, soldierNationality, soldierGender);

                }else{
                    toastMessage("Please fill in all the fields");
                }
               }
        });

        String fusion = soldierNameString + " " + soldierSurname;
        dialogBuilder.setTitle("Update Soldier, " + fusion);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private boolean updateUser(String id, String name, String surname, String address, String nationality, String gender){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(id);

        Users users = new Users(id, name, surname, address, nationality, gender);

        databaseReference.setValue(users);
        return true;
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