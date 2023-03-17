package com.example.recycletrackapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Dashboard extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //initialize date attributes
    TextView generalDate;
    Button refreshBtn;
    ArrayAdapter dataAr;
    DBHelper dbhelper;
    ListView listOfData;
    TextView resultRecycled;
    TextView resultGeneral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        resultGeneral = findViewById(R.id.resultGeneral);
        resultRecycled = findViewById(R.id.resultRecycled);

        //refresh data
        refreshBtn = findViewById(R.id.refreshBTN);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper = new DBHelper(Dashboard.this);
                listOfData = findViewById(R.id.dataList);
                List<LogModel> data = dbhelper.getDBdata();
                dataAr = new ArrayAdapter<LogModel>(Dashboard.this, android.R.layout.simple_list_item_1, data);
                Toast.makeText(Dashboard.this, "Fetched Data", Toast.LENGTH_SHORT).show();
                listOfData.setAdapter(dataAr);
                resultGeneral.setText("5");
                resultRecycled.setText("13");
            }
        });


        //Date Selection Tool Trigger and listener
        generalDate = (TextView) findViewById(R.id.editBBin);
        generalDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                GeneralDialog();
            }
        });

        //Navigation set-up
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        return true;

                    case R.id.analytics:
                        startActivity(new Intent(getApplicationContext(), Analytics.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Help.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.logwaste:
                        startActivity(new Intent(getApplicationContext(), Logwaste.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    //Open calendar app to create reminder/event
    public void startCalendar(View view){
        Intent startCalendar = new Intent(Intent.ACTION_INSERT);
        //check if a date is chosen
        if (!generalDate.getText().toString().equals("")) {
            startCalendar.setData(CalendarContract.Events.CONTENT_URI);
            startCalendar.putExtra(CalendarContract.Events.TITLE, "Bin collection");
            //check if a calendar app is available
            if (startCalendar.resolveActivity(getPackageManager()) != null){
                startActivity(startCalendar);
            } else {
                Toast.makeText(this, "No Calendar App Synced!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Date Selected!", Toast.LENGTH_SHORT).show();
        }
    }

    //API level 24 because of calender dialog, otherwise it was API level 16
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void GeneralDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    //Set text field to chosen date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = year + "-" + month + "-" + day;
        generalDate.setText(date);
    }
}