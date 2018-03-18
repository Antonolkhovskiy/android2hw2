package com.example.anton.android2hw1;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddBiography extends AppCompatActivity {

    private EditText first_name_input;
    private EditText last_name_input;
    private EditText biography_input;
    private Button buttonSave;
    private SharedPreferences sharedPreferences;
    private ArrayList<Person> persons;
    private Person person;
    private PersonsProvider personsProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biography);

        first_name_input = (EditText) findViewById(R.id.first_name_input);
        last_name_input = (EditText) findViewById(R.id.last_name_input);
        biography_input = (EditText) findViewById(R.id.biography_input);
        buttonSave = (Button) findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
            }
        });




    }

    /*   public void onClickAddName(View view) {
      // Add a new student record
      ContentValues values = new ContentValues();
      values.put(StudentsProvider.NAME,
         ((EditText)findViewById(R.id.editText2)).getText().toString());

      values.put(StudentsProvider.GRADE,
         ((EditText)findViewById(R.id.editText3)).getText().toString());

      Uri uri = getContentResolver().insert(
         StudentsProvider.CONTENT_URI, values);

      Toast.makeText(getBaseContext(),
         uri.toString(), Toast.LENGTH_LONG).show();
   }*/



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

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();

    }

    public void savePersons(){
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(persons);
        editor.putString("persons", json);
        editor.apply();
    }

    public void loadPersons(){
        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("persons", null);
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        persons = gson.fromJson(json, type);

        if (persons == null){
            persons = new ArrayList<>();
        }
    }



}
