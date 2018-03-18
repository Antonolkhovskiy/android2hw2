package com.example.anton.android2hw1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anton on 10.03.2018.
 */

public class PersonAdapter extends ArrayAdapter<Person> {

    public PersonAdapter(@NonNull Context context, List<Person> persons) {

        super(context, R.layout.single_item, persons);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.single_item, parent, false);

        Person person = (Person) getItem(position);

        TextView firstName = (TextView) customView.findViewById(R.id.firstName);
        TextView lastName = (TextView) customView.findViewById(R.id.lastName);
        TextView about_celeb = (TextView) customView.findViewById(R.id.about_celeb);

        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        about_celeb.setText(person.getPersonBiography());

        return customView;

    }

}
