package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderPizza extends AppCompatActivity {
    TextView tv,tv2;
    String pesanan = "";
    String topping;
    Button b1,btpeperoni, btkeju,btjamur,btsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);
        tv = findViewById(R.id.textView14);
        tv2 = findViewById(R.id.tvpizza);
        b1 = findViewById(R.id.button7);
        btpeperoni = findViewById(R.id.btnpeperoni);
        btkeju = findViewById(R.id.btncheese);
        btjamur = findViewById(R.id.btnmushroom);
        btsubmit = findViewById(R.id.btnsubmit);
        btpeperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topping = "Peperoni ";
                pesanan = pesanan + topping;
                tv2.setText(pesanan);
                btpeperoni.setEnabled(false);
            }
        });
        btkeju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topping = "Cheese ";
                pesanan = pesanan + topping;
                tv2.setText(pesanan);
                btkeju.setEnabled(false);
            }
        });
        btjamur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topping = "Mushroom ";
                pesanan = pesanan + topping;
                tv2.setText(pesanan);
                btjamur.setEnabled(false);
            }
        });

        Intent intent = getIntent();
        if (intent!=null){

        }
        String akun = intent.getStringExtra("username");
        tv.setText(akun);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (OrderPizza.this, indexActivity.class);
                i.putExtra("username",akun);
                startActivity(i);
            }
        });
        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.86.27:7000/order";
                JSONObject object = new JSONObject();
                try {
                    object.put("nama", akun);
                    object.put("pizza", pesanan);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(OrderPizza.this, "Order Berhasil", Toast.LENGTH_SHORT).show();
                            String msg = response.getString("response");

                            if (msg!=null)
                            {
                                Intent intent = new Intent(OrderPizza.this,indexActivity.class);
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
                                Toast.makeText(OrderPizza.this, "Order Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(OrderPizza.this).add(stringRequest);
            }
        });
    }
}