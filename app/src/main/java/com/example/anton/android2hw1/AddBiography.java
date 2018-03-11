package com.example.anton.android2hw1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biography);

        first_name_input = (EditText) findViewById(R.id.first_name_input);
        last_name_input = (EditText) findViewById(R.id.last_name_input);
        biography_input = (EditText) findViewById(R.id.biography_input);
        buttonSave = (Button) findViewById(R.id.buttonSave);





    }

    public void addPerson(){
        String fName = first_name_input.getText().toString();
        String lName = last_name_input.getText().toString();
        String bio = biography_input.getText().toString();

        Person person = new Person(fName, lName, bio);

        persons.add(person);

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
