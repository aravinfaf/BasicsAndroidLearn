package com.e.learn.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.e.learn.Constants;
import com.e.learn.R;
import java.util.Calendar;
import java.util.UUID;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkManagerMain extends AppCompatActivity {

    @BindView(R.id.alert_time)
    EditText alert_timeET;
    @BindView(R.id.set_alert_button)
    Button set_alert_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager_main);
        ButterKnife.bind(WorkManagerMain.this);

        set_alert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputValue = alert_timeET.getText().toString().trim();

                String tag = generatekey();

                int minutesbeforeAlert = Integer.valueOf(inputValue);
                long alerttime = getAlertTime(minutesbeforeAlert) - System.currentTimeMillis();
                long current = System.currentTimeMillis();

                int random = (int) (Math.random() * 50 + 1);

                Data data = createWorkInputData(Constants.TITLE, Constants.TEXT, random);
                NotificationWorker.schedulework(alerttime, data, tag);
            }
        });
    }

    private long getAlertTime(int minutesbeforeAlert) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minutesbeforeAlert);
        return calendar.getTimeInMillis();
    }

    private Data createWorkInputData(String extraTitle, String extraText, int randon) {

        return new Data.Builder()
                .putString(Constants.EXTRA_TITLE, extraTitle)
                .putString(Constants.EXTRA_TEXT, extraText)
                .putInt(Constants.EXTRA_ID, randon)
                .build();
    }

    public String generatekey() {
        return UUID.randomUUID().toString();
    }
}
