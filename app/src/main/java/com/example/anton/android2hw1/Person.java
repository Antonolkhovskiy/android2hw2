package com.example.anton.android2hw1;

/**
 * Created by Anton on 10.03.2018.
 */

public class Person {
    private String firstName;
    private String lastName;
    private String personBiography;
    private int _ID;

    public Person(){

    }

    public Person(String firstName, String lastName, String personBiography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personBiography = personBiography;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonBiography() {
        return personBiography;
    }

    public void setPersonBiography(String personBiography) {
        this.personBiography = personBiography;
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int _ID) {
        this._ID = _ID;
    }
}
