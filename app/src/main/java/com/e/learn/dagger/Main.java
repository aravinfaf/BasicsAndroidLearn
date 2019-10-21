package com.e.learn.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.e.learn.R;

import javax.inject.Inject;

public class Main extends AppCompatActivity implements View.OnClickListener {

    EditText inUsername, inNumber;
    Button btnSave, btnGet;

    MyComponent component;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initViews();
        component = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).build();
        component.inject(this);

    }
    private void initViews() {
        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        inUsername = findViewById(R.id.inUsername);
        inNumber = findViewById(R.id.inNumber);
        btnSave.setOnClickListener(this);
        btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnGet:
                inUsername.setText(sharedPreferences.getString("username", "default"));
                inNumber.setText(sharedPreferences.getString("number", "12345"));
                break;
            case R.id.btnSave:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", inUsername.getText().toString().trim());
                editor.putString("number", inNumber.getText().toString().trim());
                editor.apply();
                break;

        }
    }
}
