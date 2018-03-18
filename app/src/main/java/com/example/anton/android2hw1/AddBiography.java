package com.example.anton.android2hw1;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBiography extends AppCompatActivity {

    private EditText first_name_input;
    private EditText last_name_input;
    private EditText biography_input;
    private Button buttonSave;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biography);

        first_name_input = (EditText) findViewById(R.id.first_name_input);
        last_name_input = (EditText) findViewById(R.id.last_name_input);
        biography_input = (EditText) findViewById(R.id.biography_input);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean finished = sharedPreferences.getBoolean("finished", true);

        Toast.makeText(this, String.valueOf(finished) + "   asdasdasdasdas", Toast.LENGTH_LONG);

        if(finished == false){
            contBio();
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
            }
        });



    }

    @Override
    protected void onPause() {
        saveIncompletedBio();
        super.onPause();
    }

    public void addPerson(){
        String fName = first_name_input.getText().toString();
        String lName = last_name_input.getText().toString();
        String bio = biography_input.getText().toString();

        ContentValues contentValues = new ContentValues();

        contentValues.put(PersonsProvider.FIRST_NAME, fName);
        contentValues.put(PersonsProvider.LAST_NAME, lName);
        contentValues.put(PersonsProvider.BIOGRAPHY, bio);

        Uri uri = getContentResolver().insert(
                PersonsProvider.CONTENT_URI, contentValues);

        editor.clear().commit();
        editor.putBoolean("finished", true);
        editor.commit();
        flag = false;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

   public void contBio(){
        String fName = sharedPreferences.getString("fname", "john");
        String lName = sharedPreferences.getString("lname", "smith");
        String bio = sharedPreferences.getString("bio", "cool guy");

        first_name_input.setText(fName);
        last_name_input.setText(lName);
        biography_input.setText(bio);
   }

   public void saveIncompletedBio(){
       String fname = first_name_input.getText().toString();
       String lname = last_name_input.getText().toString();
       String bio = biography_input.getText().toString();
       if(flag){
           editor.putBoolean("finished", false);
           editor.putString("fname", fname);
           editor.putString("lname", lname);
           editor.putString("bio", bio);
           editor.commit();
       }

   }



}
