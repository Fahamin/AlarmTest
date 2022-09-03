package com.bd.durbin.alarmtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class MyBroadcastReceiver extends BroadcastReceiver {

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Intent intent;
    String sceondValue ="20";

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent in = new Intent(context, MakeMyToast.class);
        context.startService(in);
        sceondValue = intent.getStringExtra("sceond");
        setAlarm(context);
    }

    public void setAlarm(Context context) {

        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(12000);

        intent = new Intent(context, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 280192, intent, PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_IMMUTABLE);
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                Calendar.getInstance().getTimeInMillis() + (Integer.parseInt(sceondValue) * 1000), 10000
                , pendingIntent);
//Next alarm in 15s
    }
}
