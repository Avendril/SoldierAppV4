package com.example.nexer.soldierappv4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profile extends Fragment {
    private static final String TAG = "ViewDatabase";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth firebaseAuth2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference databaseReference;

    String userID;

    private ListView mListView;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");

        firebaseAuth2 = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = mFirebaseDatabase.getReference();
        final FirebaseUser user = firebaseAuth2.getCurrentUser();

        mListView = (ListView)getView().findViewById(R.id.listview);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showData(dataSnapshot);
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(user != null){
                        String mUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        UserInformation uInfo = new UserInformation();
                        uInfo.setName(ds.child(mUid).getValue(UserInformation.class).getName()); //set the name
                        uInfo.setEmail(ds.child(mUid).getValue(UserInformation.class).getEmail()); //set the email
                        uInfo.setAge(ds.child(mUid).getValue(UserInformation.class).getAge()); //set the phone_num

                        //display all the information
                        Log.d(TAG, "showData: name: " + uInfo.getName());
                        Log.d(TAG, "showData: email: " + uInfo.getEmail());
                        Log.d(TAG, "showData: age: " + uInfo.getAge());

                        ArrayList<String> array  = new ArrayList<>();
                        // array.add(uInfo.getName());
                        array.add(uInfo.getEmail());
                        array.add(uInfo.getAge());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,array);
                        mListView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onStart(){
        super.onStart();;
        firebaseAuth2.addAuthStateListener(mAuthListener);
    }

    public void onStop(){
        super.onStop();;
        if(mAuthListener != null){
            firebaseAuth2.removeAuthStateListener(mAuthListener);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(getActivity(),message ,Toast.LENGTH_SHORT).show();
    }

//    private void showData(DataSnapshot dataSnapshot){
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            UserInformation uInfo = new UserInformation();
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getName());
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getSurname());
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getAge());
//            uInfo.setName(ds.child(userID).getValue(UserInformation.class).getEmail());
//
//            Log.d(TAG, "showData: name:" + uInfo.getName());
//            Log.d(TAG, "showData: surname:" + uInfo.getSurname());
//            Log.d(TAG, "showData: age:" + uInfo.getAge());
//            Log.d(TAG, "showData: email:" + uInfo.getEmail());
//
//            ArrayList<String> arrayList = new ArrayList<>();
//
//            arrayList.OptionsCRUD(uInfo.getName());
//            arrayList.OptionsCRUD(uInfo.getSurname());
//            arrayList.OptionsCRUD(uInfo.getAge());
//            arrayList.OptionsCRUD(uInfo.getEmail());
//
//            ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList);
//            mListView.setAdapter(adapter);
//        }
//    }
}