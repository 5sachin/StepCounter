package com.example.stepcounter.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import com.example.stepcounter.Database.Database;
import com.example.stepcounter.R;
import com.example.stepcounter.Utils.Util;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Report extends Fragment {
    public static NumberFormat formatter=NumberFormat.getInstance(Locale.getDefault());
    private TextView totalsteps,totalCalories,totldistance,dateView,monthView;
    private String unit;
    private int todayoffset,total_start,since_boot, total_today_steps;
    CalendarView calendarView;
    String[] arr = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    String prefsUnit;
    Calendar date = Calendar.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_report,null);

        totalCalories=view.findViewById(R.id.caloriestotal);
        totalsteps=view.findViewById(R.id.totalstep);
        totldistance=view.findViewById(R.id.distancetotal);
        calendarView = view.findViewById(R.id.calendar);
        dateView = view.findViewById(R.id.dateDisplay);
        //monthView = view.findViewById(R.id.monthDisplay);

        final Database db=Database.getInstance(getActivity());
        todayoffset = db.getSteps(Util.getToday());
        since_boot = FragmentHome.since_boot;
        total_today_steps = Math.max(todayoffset+FragmentHome.since_boot,0);
        total_start = db.getTotalWithoutToday();

        totalsteps.setText(formatter.format(total_today_steps));
        double kcal=(total_today_steps)*0.04;
        totalCalories.setText(formatter.format(kcal));

        SharedPreferences prefs = getActivity().getSharedPreferences("pedometer", Context.MODE_PRIVATE);
        final float stepsize=prefs.getFloat("stepsize_value",MyProfile.DEFAULT_STEP_SIZE);
        float distance_total=(total_today_steps)*stepsize;

        prefsUnit = prefs.getString("stepsize_unit",MyProfile.DEFAULT_STEP_UNIT);
        if(prefsUnit.equals("cm"))
        {
            distance_total/=100000;
            unit="km";

        }else{
            distance_total /= 5280;
            unit="mile";
        }

        totldistance.setText(formatter.format(distance_total)+" "+unit);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                try {
                    //monthView.setText(arr[month].toString());
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
                    String todayDate = dateFormat.format(c);
                    int stepOfDate = 0;
                    long dateReq = 0;
                    if((Integer.parseInt(todayDate) - dayOfMonth)>0){
                        dateReq = Util.getPrevious(Integer.parseInt(todayDate) - dayOfMonth);
                        stepOfDate = db.getSteps(dateReq);
                    }else{
                        dateReq = Util.getPrevious( 31-dayOfMonth+Integer.parseInt(todayDate));
                        stepOfDate = db.getSteps(dateReq);
                    }


                    Log.v("DataStep87",": "+stepOfDate+": "+dateReq+": "+todayDate);
                    totalsteps.setText(String.valueOf(stepOfDate));
                    Log.v("DataStep89",": "+stepOfDate+": "+dateReq);
                    dateView.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
                    double kcal = stepOfDate * 0.04;
                    totalCalories.setText(formatter.format(kcal));
                    float distance_total = stepsize * stepOfDate;
                    Log.v("DataStep",distance_total+": "+stepOfDate+": "+dateReq);
                    if (prefsUnit.equals("cm")) {
                        distance_total /= 100000;
                        unit = "km";
                    } else {
                        distance_total /= 5280;
                        unit = "mile";
                    }
                    totldistance.setText(formatter.format(distance_total) + " " + unit);
                }catch(Exception e){
                    Log.v("DataStep",e.getMessage());
                }
            }
        });
        return view;
    }
}