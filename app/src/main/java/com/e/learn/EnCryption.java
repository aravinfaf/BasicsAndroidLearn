package com.e.learn;

import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.e.learn.workmanager.AES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnCryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_cryption);

        final String secretKey = "ssshhhhhhhhhhh!!!!";

        String originalString = "erpadmin";


        BasicAuthInterceptor basicAuthInterceptor=new BasicAuthInterceptor(originalString,originalString);

        LoginService loginService=Auth.createService(LoginService.class,"erpadmin","erpadmin");
//        Call<User> call=loginService.basicLogin();
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//                if (response.isSuccessful()) {
//                    Toast.makeText(EnCryption.this, "SSSSS", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(EnCryption.this, "NNNNNNN", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(EnCryption.this, "RRRRRR", Toast.LENGTH_SHORT).show();
//            }
//        });




        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
        try {
            //Log.e("EEEE",AESS.encrypt("KGISL","kgisl","erpadmin"));

            //String enc=AESS.encrypt("KGISL","kgisl","erpadmin");

            Log.e("EEEE",AESS.decrypt("KGISL","kgisl","KPxI0x6OXJQthAS1XRlAsJ7FkYJob9P6Po9056ZcqkIaI1U1RqBGXAz1XvuAgh3l"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
