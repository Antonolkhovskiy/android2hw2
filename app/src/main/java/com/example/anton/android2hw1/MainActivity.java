package com.example.anton.android2hw1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ArrayList<Person> persons;
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String FULL_BIO = "fullBio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean finished = sharedPreferences.getBoolean("finished", true);

        if(finished == false){
            Intent intent = new Intent(this, AddBiography.class);
            startActivity(intent);
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        persons = new ArrayList<>();

        buildListView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addItem: {
                activityAdd();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }

    }

    public void activityAdd(){
        Intent intent = new Intent(this, AddBiography.class );
        startActivity(intent);
    }


    public void retrieveAndShowPersons() {
        String URL = "content://com.example.anton.android2hw1";
        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "first_name");

        if (c.moveToFirst()) {
            do {
                Person person = new Person(c.getString(c.getColumnIndex(PersonsProvider.FIRST_NAME))
                        ,c.getString(c.getColumnIndex(PersonsProvider.LAST_NAME))
                        ,c.getString(c.getColumnIndex(PersonsProvider.BIOGRAPHY)));
                persons.add(person);


            } while (c.moveToNext());


        }
    }

    public void buildListView(){
        retrieveAndShowPersons();
        ListAdapter personAdapter = new PersonAdapter(this, persons);
        ListView mainList = (ListView)findViewById(R.id.mainList);
        mainList.setAdapter(personAdapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ShowFullBio.class);
                Person personSelected = persons.get(i);
                intent.putExtra(FIRST_NAME, personSelected.getFirstName());
                intent.putExtra(LAST_NAME, personSelected.getLastName());
                intent.putExtra(FULL_BIO, personSelected.getPersonBiography());

                startActivity(intent);

            }
        });



    }

}
