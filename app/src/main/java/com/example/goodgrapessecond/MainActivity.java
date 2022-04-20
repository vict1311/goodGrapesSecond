package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create Button object that has the id of the button in XML
        final Button button = (Button) findViewById(R.id.button);
        final Button buttonNewActivity= (Button) findViewById(R.id.buttonNewActivity);

        TextView text = (TextView) findViewById(R.id.text);


        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        text.setText("Hello");
                    }
                }
        );

        buttonNewActivity.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openNewActivity();
                    }
                }
        );

            }
        public void openNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
        }

