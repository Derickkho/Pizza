package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignActivity extends AppCompatActivity {
    Button b1,b2;
    EditText account,pass,email;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        account = findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPassword2);
        email = findViewById(R.id.editTextTextEmail);
        b1 = findViewById(R.id.SignUpBut);
        b2 = findViewById(R.id.LoginButton);
        i = new Intent(SignActivity.this,MainActivity.class);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String akun = account.getText().toString();
                String password = pass.getText().toString();
                String em = email.getText().toString();
                String url = "http://192.168.86.27:7000/signup";
                JSONObject object = new JSONObject();
                try {
                    object.put("email", em);
                    object.put("pass", password);
                    object.put("nama", akun);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(SignActivity.this, "Sign Up Berhasil", Toast.LENGTH_SHORT).show();
                            String msg = response.getString("response");

                            if (msg!=null)
                            {
                                Intent intent = new Intent(SignActivity.this,MainActivity.class);
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
                                Toast.makeText(SignActivity.this, "Sign Up Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(SignActivity.this).add(stringRequest);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }
}