package com.aegroup.esmaeelnapil.tzzzz;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.Calendar;

import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity {


    RelativeLayout relativeLayout;
    GifImageButton gf;
    CircularProgressButton cr;
    Button setalarm, cancelalarm;
    EditText editm, edith;
    int alarm_number;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TimePicker timePicker;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // REMOVE TITLE
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setalarm = (Button) findViewById(R.id.button7);
        cancelalarm = (Button) findViewById(R.id.button8);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
//        edith=(EditText)findViewById(R.id.edith);
//        editm=(EditText)findViewById(R.id.editm);


            hideitems();

        relativeLayout = (RelativeLayout) findViewById(R.id.content_main);

        spinner = (Spinner) findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.alarm_number, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = adapter.getItem(position).toString();
                Toast.makeText(getApplicationContext(), str + " Selected", Toast.LENGTH_SHORT).show();



                switch (str) {
                    case "Chose alarm from here":

                    hideitems();
//                        editm.setVisibility(View.INVISIBLE);
//                        edith.setVisibility(View.INVISIBLE);


                        break;
                    case "Morning":
                      showitems();
                        alarm_number = 0;
//                        editm.setVisibility(View.VISIBLE);
//                        edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;

                    case "Afternoon":
                   showitems();
                        alarm_number = 1;
//                        editm.setVisibility(View.VISIBLE);
//                        edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;

                    case "Night":
                      showitems();
                        alarm_number = 2;
//                        editm.setVisibility(View.VISIBLE);
//                        edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;

                    case "M":
                       showitems();
                        alarm_number = 3;
//                        editm.setVisibility(View.VISIBLE);
//                        edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;

                    case "A":
                       showitems();
                        alarm_number = 4;
//                        editm.setVisibility(View.VISIBLE);
//                        edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;

                    case "N":
                        showitems();
                        alarm_number = 5;
//                            editm.setVisibility(View.VISIBLE);
//                            edith.setVisibility(View.VISIBLE);
                        cancelalarm.setBackgroundColor(0xFFE4E3E3);
                        break;


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(), "You didnot chose any ", Toast.LENGTH_SHORT).show();

            }
        });


        setalarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {    // Button 7

                int h = timePicker.getCurrentHour();
                int m = timePicker.getCurrentMinute();

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY, h);
                calendar.set(Calendar.MINUTE, m);
                calendar.set(calendar.SECOND, 0);


                Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, alarm_number, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                Toast.makeText(getApplicationContext(), "Time set to " + calendar.getTime().toString() + "It will ring every day in the same time"
                        , Toast.LENGTH_LONG).show();

                relativeLayout.setBackgroundResource(R.drawable.bg);
                if (alarm_number == 0) {
                    Toast.makeText(getApplicationContext(), "Morning Alarm Set successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 1) {
                    Toast.makeText(getApplicationContext(), "Afternon Alarm Set successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 2) {
                    Toast.makeText(getApplicationContext(), "Night Alarm Set successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 3) {
                    Toast.makeText(getApplicationContext(), "M morning Set successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 4) {
                    Toast.makeText(getApplicationContext(), "A afternon Set successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 5) {
                    Toast.makeText(getApplicationContext(), "N night Alarm Set successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pleas Chose from the spinner", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancelalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
                Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, alarm_number, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

                am.cancel(pendingIntent);
                relativeLayout.setBackgroundResource(R.drawable.bg2);


                if (alarm_number == 0) {
                    Toast.makeText(getApplicationContext(), "Morning Alarm Canceld successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 1) {
                    Toast.makeText(getApplicationContext(), "Afternon Alarm Canceld successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 2) {
                    Toast.makeText(getApplicationContext(), "Night Alarm Canceld successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 3) {
                    Toast.makeText(getApplicationContext(), "M morning Canceld successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 4) {
                    Toast.makeText(getApplicationContext(), "A afternon Canceld successfully", Toast.LENGTH_SHORT).show();
                } else if (alarm_number == 5) {
                    Toast.makeText(getApplicationContext(), "N night Alarm Canceld successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pleas Chose from the spinner", Toast.LENGTH_SHORT).show();
                }

                cancelalarm.setBackgroundColor(Color.WHITE);
            }
        });


    }

    public void showitems() {
        setalarm.setVisibility(View.VISIBLE);
        cancelalarm.setVisibility(View.VISIBLE);
        timePicker.setVisibility(View.VISIBLE);
    }

    public void hideitems() {
        setalarm.setVisibility(View.INVISIBLE);
        cancelalarm.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content_main);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case R.id.red:
//                relativeLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
//                break;
//
//            case R.id.action_settings:
//                relativeLayout.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
//                Toast.makeText(getApplicationContext(), "you pressed Settings", Toast.LENGTH_SHORT).show();
//                break;
//
//            case R.id.blue:
//                relativeLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
//                break;
//
//            case R.id.yellow:
//                relativeLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
//                break;
//
//
//        }

        return super.onOptionsItemSelected(item);
    }
}
