package com.example.nexer.soldierappv4;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UsersList extends ArrayAdapter<Users> {
    private Activity context;
    private List<Users> Users;

    public UsersList(Activity context, List<Users> Users) {
        super(context, R.layout.layout_users_list, Users);
        this.context = context;
        this.Users = Users;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_users_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewSurname = (TextView) listViewItem.findViewById(R.id.textViewSurname);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewNationality = (TextView) listViewItem.findViewById(R.id.textViewNationality);
        TextView textViewGender = (TextView) listViewItem.findViewById(R.id.textViewGender);

        Users users = Users.get(position);
       // textViewName.setText(com.example.nexer.soldierappv4.Users.getUserName());
       // textViewSurname.setText(com.example.nexer.soldierappv4.Users.getUserSurname());
       // textViewAddress.setText(com.example.nexer.soldierappv4.Users.getUserAddress());
       // textViewNationality.setText(com.example.nexer.soldierappv4.Users.getUserNationality());
       // textViewGender.setText(com.example.nexer.soldierappv4.Users.getUserGender());

        return listViewItem;
    }
}