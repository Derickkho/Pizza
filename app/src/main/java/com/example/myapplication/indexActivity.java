package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class indexActivity extends AppCompatActivity {
    Button b1, b2,b3,b4;
    TextView tv;
    String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button5);
        b3 = findViewById(R.id.button4);
        b4 = findViewById(R.id.button6);
        tv = findViewById(R.id.tv1);
        Intent intent = getIntent();
        if (intent!=null){

        }
        String akun = intent.getStringExtra("username");
        tv.setText(akun);
        nama = akun;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent i = new Intent(indexActivity.this,OrderPizza.class);
                i.putExtra("username",nama);
                startActivity(i);;
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(indexActivity.this,OpenPizza.class);
                i.putExtra("username",nama);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(indexActivity.this,RatePizza.class);
                i.putExtra("username",nama);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (indexActivity.this, MainActivity.class));
                nama = null;
            }
        });
    }
}