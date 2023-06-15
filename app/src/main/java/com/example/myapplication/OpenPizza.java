package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OpenPizza extends AppCompatActivity {
    TextView tv;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pizza);
        tv = findViewById(R.id.textView17);
        b1 = findViewById(R.id.button8);
        Intent intent = getIntent();
        if (intent!=null){

        }
        String akun = intent.getStringExtra("username");
        tv.setText(akun);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (OpenPizza.this, indexActivity.class);
                i.putExtra("username",akun);
                startActivity(i);
            }
        });
    }
}