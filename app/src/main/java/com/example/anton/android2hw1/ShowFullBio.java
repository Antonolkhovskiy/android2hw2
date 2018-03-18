package com.example.anton.android2hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ShowFullBio extends AppCompatActivity {
    private TextView showFirstName;
    private TextView showLastName;
    private TextView showBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_bio);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showFirstName = (TextView)findViewById(R.id.show_first_name);
        showLastName = (TextView)findViewById(R.id.show_last_name);
        showBio = (TextView)findViewById(R.id.show_biography);

        Intent intent = getIntent();

        String first_name = intent.getStringExtra(MainActivity.FIRST_NAME);
        String last_name = intent.getStringExtra(MainActivity.LAST_NAME);
        String biography = intent.getStringExtra(MainActivity.FULL_BIO);

        showFirstName.setText(first_name);
        showLastName.setText(last_name);
        showBio.setText(biography);


    }
}
