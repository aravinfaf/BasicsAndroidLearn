package com.e.learn.gmail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.e.learn.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoogleSignIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;
    @BindView(R.id.textView)
    TextView textView;

    GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        ButterKnife.bind(GoogleSignIn.this);

        GoogleSignInOptions go=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,go)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(i,RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {

        if(result.isSuccess()){
            Toast.makeText(GoogleSignIn.this, "Success.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(GoogleSignIn.this,ProfileActivity.class));
        }else {
            Toast.makeText(GoogleSignIn.this, "Failure."+result.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }
}
