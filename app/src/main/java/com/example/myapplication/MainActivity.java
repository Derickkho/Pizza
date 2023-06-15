package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    EditText account,pass;
    Button b1,b2;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.signupbutton);
        account = findViewById(R.id.editTextTextAccount);
        pass = findViewById(R.id.editTextTextPassword);
        i = new Intent(MainActivity.this,indexActivity.class);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent (MainActivity.this, SignActivity.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String akun = account.getText().toString();
                String password = pass.getText().toString();
                String url = "http://192.168.86.27:7000/login";
                JSONObject object = new JSONObject();
                try {
                    object.put("account", akun);
                    object.put("pass", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(MainActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                            String msg = response.getString("message");

                            if (msg.equals("Login successful"))
                            {
                                Intent intent = new Intent(MainActivity.this,indexActivity.class);
                                intent.putExtra("username",akun);
                                startActivity(intent);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("apiresult", error.getMessage() != null ? error.getMessage() : "Unknown Error");
                                Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(MainActivity.this).add(stringRequest);
            }
        });
    }
}
