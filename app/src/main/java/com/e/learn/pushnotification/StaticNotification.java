package com.e.learn.pushnotification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.e.learn.R;

public class StaticNotification extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK = 16;
    EditText ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_notification);

        ET=findViewById(R.id.ET);

        ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean hasCallPhonePermission = ContextCompat.checkSelfPermission(StaticNotification.this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;

                if (hasCallPhonePermission)
                    startActivity(createCallIntentFromNumber());
                else
                    Toast.makeText(StaticNotification.this, "Allow Permission", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Intent createCallIntentFromNumber() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        String number = ET.getText().toString();
        intentToCall.setData(Uri.parse("tel:" + number));
        return intentToCall;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                ET.setText(data.getExtras()
                        .getString(""));
            }
        }
    }
}
