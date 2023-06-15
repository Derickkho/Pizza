package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RatePizza extends AppCompatActivity {
    TextView tv;
    Button b1,btsubmit;
    EditText editref,editrate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_pizza);
        tv = findViewById(R.id.textnama);
        btsubmit = findViewById(R.id.btnngerate);
        b1 = findViewById(R.id.button3);
        editref = findViewById(R.id.editTextNumber);
        editrate = findViewById(R.id.editTextNumber2);
        Intent intent = getIntent();
        if (intent!=null){

        }
        String akun = intent.getStringExtra("username");
        tv.setText(akun);

        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = editref.getText().toString();
                String b = editrate.getText().toString();
                String url = "http://192.168.86.27:7000/rate";
                JSONObject object = new JSONObject();
                try {
                    object.put("no", a);
                    object.put("nilai",b);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(RatePizza.this, "Rating Berhasil", Toast.LENGTH_SHORT).show();
                            String msg = response.getString("response");

                            if (msg!=null )
                            {
                                Intent intent = new Intent(RatePizza.this,indexActivity.class);
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
                                Toast.makeText(RatePizza.this, "Order Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(RatePizza.this).add(stringRequest);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (RatePizza.this, indexActivity.class);
                i.putExtra("username",akun);
                startActivity(i);
            }
        });

        String url = "http://192.168.86.27:7000/hist";
        JSONObject object = new JSONObject();
        try {
            object.put("nama", akun);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String msg = response.getString("response");
                    TextView tvref;
                    tvref = findViewById(R.id.textviewref);
                    tvref.setText(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("apiresult", error.getMessage() != null ? error.getMessage() : "Unknown Error");

                    }
                });
        Volley.newRequestQueue(RatePizza.this).add(stringRequest);
    }
}