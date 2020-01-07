package com.e.learn.blockdates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.e.learn.MainActivity;
import com.e.learn.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BlockDatesActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    DatePickerDialog datePickerDialog ;
    TimePickerDialog timePickerDialog ;
    int Year, Month, Day, Hour, Minute;
    Calendar calendar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_dates);


        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);
        Hour = calendar.get(Calendar.HOUR_OF_DAY);
        Minute = calendar.get(Calendar.MINUTE);

        //getWeekendDays();

        final Button button_datepicker = (Button) findViewById(R.id.button_datepicker);
        button_datepicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(BlockDatesActivity.this, Year, Month, Day);
                datePickerDialog.setThemeDark(false);
                datePickerDialog.showYearPickerFirst(false);
                datePickerDialog.setTitle("Date Picker");


                // Setting Min Date to today date
                Calendar min_date_c = Calendar.getInstance();
                datePickerDialog.setMinDate(min_date_c);



                // Setting Max Date to next 2 years
                Calendar max_date_c = Calendar.getInstance();
                max_date_c.set(Calendar.YEAR, Year+2);
                datePickerDialog.setMaxDate(max_date_c);



                //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
                for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
                    int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                        Calendar[] disabledDays =  new Calendar[1];
                        disabledDays[0] = loopdate;
//                        datePickerDialog.setDisabledDays(disabledDays);
                        datePickerDialog.setHighlightedDays(disabledDays);
                        }
                }

                String[] holidays = {"07-01-2020","05-01-2020","12-01-2020",
                                     "09-01-2020","10-01-2020","13-01-2020",
                                     "07-02-2020","20-01-2020","25-01-2020"};

                java.util.Date date = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

//                for (int i = 0;i < holidays.length; i++) {
//
//                    try {
//                        date = sdf.parse(holidays[i]);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//
//                    calendar = dateToCalendar(date);
//                    System.out.println(calendar.getTime());
//
//                    List<Calendar> dates = new ArrayList<>();
//                    dates.add(calendar);
//                    Calendar[] disabledDays1 = dates.toArray(new Calendar[dates.size()]);
//                    datePickerDialog.setDisabledDays(disabledDays1);
//                }


                datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        Toast.makeText(BlockDatesActivity.this, "Datepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });


        final Button button_timepicker = (Button) findViewById(R.id.button_timepicker);
        button_timepicker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timePickerDialog = TimePickerDialog.newInstance(BlockDatesActivity.this, Hour, Minute,false );
                timePickerDialog.setThemeDark(false);
                //timePickerDialog.showYearPickerFirst(false);
                timePickerDialog.setTitle("Time Picker");

                timePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                        Toast.makeText(BlockDatesActivity.this, "Timepicker Canceled", Toast.LENGTH_SHORT).show();
                    }
                });

                timePickerDialog.show(getFragmentManager(), "TimePickerDialog");
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date = "Date: "+Day+"/"+(Month+1)+"/"+Year;

        Toast.makeText(BlockDatesActivity.this, date, Toast.LENGTH_LONG).show();

        TextView text_datepicker = (TextView)findViewById(R.id.text_datepicker);
        text_datepicker.setText(date);

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        String time = "Time: "+hourOfDay+"h"+minute+"m"+second;

        Toast.makeText(BlockDatesActivity.this, time, Toast.LENGTH_LONG).show();


        TextView text_timepicker = (TextView)findViewById(R.id.text_timepicker);
        text_timepicker.setText(time);
    }
    private Calendar dateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}