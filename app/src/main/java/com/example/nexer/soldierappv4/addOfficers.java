package com.example.nexer.soldierappv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addOfficers extends AppCompatActivity {
    @Nullable private Button confirm;
    @Nullable private Spinner spinnerOfficer;
    @Nullable private EditText editTextDate;
    @Nullable private TextView textViewSoldierName;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_officers);

        confirm = findViewById(R.id.buttonAddUsers);
        spinnerOfficer = findViewById(R.id.spinnerGender);
        editTextDate = findViewById(R.id.editTextDate);
        textViewSoldierName = findViewById(R.id.textViewSoldierName);

        Intent intent = getIntent();

        String id = intent.getStringExtra(preview.User_ID);
        String name = intent.getStringExtra(preview.User_Name);
        String surname = intent.getStringExtra(preview.User_Surname);

        String fusion = name + " " + surname;

        textViewSoldierName.setText(fusion);

        databaseReference = FirebaseDatabase.getInstance().getReference("AssignedOfficers").child(id);


    }

    private void saveOfficer(){
        String name = spinnerOfficer.getSelectedItem().toString();
        String date = editTextDate.getText().toString().trim();

        boolean fieldsOK = validate(new EditText[] {editTextDate});

        if(fieldsOK){
            String id = databaseReference.push().getKey();
            oficers oficers = new oficers(id,name,date);
            databaseReference.child(id).setValue(oficers);
            toastMessage("Saved Successfully!");
        }else{
            toastMessage("Please enter the date dd/mm/yyyy");
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
        Toast.makeText(this,message ,Toast.LENGTH_SHORT).show();
    }
}
