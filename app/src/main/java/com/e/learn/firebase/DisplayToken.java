package com.e.learn.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.e.learn.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DisplayToken extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button buttonDisplayToken;
    private TextView textViewToken;
    private EditText emailET;

    private static final String URL_REGISTER_DEVICE = "http://10.30.100.61/push/RegisterDevice.php";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_token);

        //getting views from xml
        emailET =  findViewById(R.id.emailET);
        textViewToken = (TextView) findViewById(R.id.textViewToken);
        buttonDisplayToken = (Button) findViewById(R.id.buttonDisplayToken);

        //adding listener to view
        buttonDisplayToken.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonDisplayToken) {
            //getting token from shared preferences
            String token = SharedPrefManager.getInstance(this).getDeviceToken();
            sendtokenToServer();
            //if token is not null
            if (token != null) {
                //displaying the token
                textViewToken.setText(token);
            } else {
                //if token is null that means something wrong
                textViewToken.setText("Token not generated");
            }
        }
    }

   public void sendtokenToServer(){

       progressDialog=new ProgressDialog(DisplayToken.this);
       progressDialog.setMessage("Loading");
       progressDialog.setCancelable(false);
       progressDialog.show();

       String token= SharedPrefManager.getInstance(DisplayToken.this).getDeviceToken();
       final String email = emailET.getText().toString().trim();

       if (token == null) {
           progressDialog.dismiss();
           Toast.makeText(this, "Token not generated", Toast.LENGTH_LONG).show();
           return;
       }

       StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGISTER_DEVICE, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
                progressDialog.dismiss();
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   Toast.makeText(DisplayToken.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(DisplayToken.this,ActivitySendPushNotification.class));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               progressDialog.dismiss();
               Toast.makeText(DisplayToken.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

           }
       }){

           protected Map<String,String> getParams(){
               Map<String,String> params=new HashMap<>();
               params.put("email",email);
               params.put("token",token);
               return params;
           }
       };
       RequestQueue requestQueue= Volley.newRequestQueue(this);
       requestQueue.add(stringRequest);

    }
}
