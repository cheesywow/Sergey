package com.sergey.michael.sergey.Engine.UI.dialogs;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import com.sergey.michael.sergey.Engine.Util.visual.NotificationService;
import com.sergey.michael.sergey.R;

import java.util.Calendar;

public class ReminderDialog extends DialogFragment {

    Button ok;
    Button cancel;
    EditText title;
    EditText content;
    EditText date;
    EditText time;
    View view;
    Activity activity;
    int globalhour = 0;
    int globalminute = 0;
    DatePickerDialog mDatePicker;
    TimePickerDialog mTimePicker;
    static int index = 0;


    public static interface OnCompleteListener {
        public abstract void onComplete(String[] strings);
    }

    private OnCompleteListener mListener;

    // make sure the Activity implemented it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        try {
            this.mListener = (OnCompleteListener) activity;
        } catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_reminder, container, false);
        getDialog().setTitle("Simple Dialog");
        date = (EditText) view.findViewById(R.id.edit_date);
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) dateListener(v);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateListener(v);
            }
        });
        time = (EditText) view.findViewById(R.id.edit_time);
        time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) timeListener(v);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeListener(v);
            }
        });

        ok = (Button) view.findViewById(R.id.dialogButtonOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                submit(getActivity().getBaseContext());
            }
        });

        cancel = (Button) view.findViewById(R.id.dialogButtonCANCEL);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    public void timeListener(View view) {
        Log.d("TIME", "FOCUSED");
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time.setText(selectedHour + ":" + selectedMinute);
                globalhour = selectedHour;
                globalminute = selectedMinute;
            }
        }, hour, minute, false);
        mTimePicker.show();
    }

    public void dateListener(View view) {
        Log.d("DATE", "CLICKED");
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    /*      Your code   to get inventory and cost    */
                selectedmonth = selectedmonth + 1;
                date.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void submit(Context context) {
        title = (EditText) view.findViewById(R.id.edit_title);
        content = (EditText) view.findViewById(R.id.edit_content);
        String[] strings = {title.getText().toString(), content.getText().toString(),
                date.getText().toString(), time.getText().toString()};
        mListener.onComplete(strings);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.set(mDatePicker.getDatePicker().getYear(), mDatePicker.getDatePicker().getMonth(),
                    mDatePicker.getDatePicker().getDayOfMonth(),
                    globalhour, globalminute, 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        long startTime = calendar.getTimeInMillis();
        int num = ReminderDialog.index;
        scheduleNotification(context, startTime - System.currentTimeMillis(), num, strings);
        getDialog().dismiss();
    }

    public void scheduleNotification(Context context, long delay, int notificationId, String[] strings) {//delay is after how much cost(in millis) from current cost you want to schedule the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle(strings[0])
                .setContentText(strings[1])
                .setSmallIcon(R.drawable.sergey)
                //.setLargeIcon(((BitmapDrawable) context.getResources().getDrawable(R.drawable.app_icon)).getBitmap())
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                ;
        Intent intent = new Intent(context, ReminderDialog.class);
        PendingIntent activity = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(activity);

        Notification notification = builder.build();
        Intent notificationIntent = new Intent(context, NotificationService.class);
        notificationIntent.putExtra(NotificationService.NOTIFICATION_ID, notificationId);
        notificationIntent.putExtra(NotificationService.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        ReminderDialog.index = ReminderDialog.index + 1;
        Log.d("SCHEDULER", "" + index);
    }
}
