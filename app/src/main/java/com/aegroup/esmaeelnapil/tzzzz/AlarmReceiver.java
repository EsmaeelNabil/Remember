package com.aegroup.esmaeelnapil.tzzzz;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Esmaeel napil on 12/9/2016.
 */

public class AlarmReceiver  extends BroadcastReceiver{
    int MID=0;
    @Override
    public void onReceive(Context context, Intent intent) {



        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, EventActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);


        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.a)

                .setContentTitle("Did you take your medicine ?")
                .setContentText("Always laugh when you can, it is cheap medicine. \n  take your medicine <3 ")
                .setSound(alarmSound)
                .setSmallIcon(R.drawable.not)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000,1000, 1000, 1000, 1000,1000, 1000, 1000, 1000,1000, 1000, 1000, 1000,1000, 1000, 1000, 1000});
        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;

    }
}
