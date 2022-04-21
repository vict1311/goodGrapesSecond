package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<User>();

        // generate user and return its userID
        //User userVictor  = new User("Victor Jensen", "vict1311", "123hej456");
        //System.out.println(userVictor.getUserID());


    }
}