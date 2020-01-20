package com.e.learn.razorpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.learn.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutActivity extends AppCompatActivity implements PaymentResultListener {

    private Button buttonConfirmOrder;
    private EditText editTextPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        buttonConfirmOrder = (Button) findViewById(R.id.buttonConfirmOrder);
        editTextPayment = (EditText) findViewById(R.id.editTextPayment);

        buttonConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextPayment.getText().toString().trim().length() == 0) {
                    Toast.makeText(CheckoutActivity.this, "Enter amount", Toast.LENGTH_SHORT).show();
                } else {
                    startpayment();
                }
            }

        });

    }

    private void startpayment() {

        final Context activity = null;
        final Checkout checkout = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "DemoCharge");
            options.put("image","https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency","INR");

            String payment=editTextPayment.getText().toString();
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject prefill=new JSONObject();
            prefill.put("email","sandaravind@gmail.com");
            prefill.put("contact","8838058359");

            options.put("prefill",prefill);

            checkout.open(CheckoutActivity.this,options);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPaymentSuccess(String s) {

        Toast.makeText(this, "Payment successfully done! " + s  , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
}
