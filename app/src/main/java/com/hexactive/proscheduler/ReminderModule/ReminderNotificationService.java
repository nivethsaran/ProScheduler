package com.hexactive.proscheduler.ReminderModule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.hexactive.proscheduler.MainModule.MainActivity;
import com.hexactive.proscheduler.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class ReminderNotificationService extends Service {
    String title,uid,date,time,note,priority,notification,rid;
    public ReminderNotificationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Alarm","Started");
        title=intent.getStringExtra("title");
        uid=intent.getStringExtra("uid");
        date=intent.getStringExtra("date");
        time=intent.getStringExtra("time");
        note=intent.getStringExtra("note");
        priority=intent.getStringExtra("pri");
        notification=intent.getStringExtra("noti");
        rid=intent.getStringExtra("rid");
        new NotificationTask().execute(title,uid,date,time,note,priority,notification,rid);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        super.onCreate();



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    public class NotificationTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String approval="false";
            try{
                String resp = Jsoup.connect("https://pro-scheduler-backend.herokuapp.com/getReminderCheck/rid/"+strings[7]).ignoreContentType(true).execute().body();
                Log.d("Alarm",resp);
                try{
//                JSONObject data=new JSONObject(json);
                    JSONArray dataArray=new JSONArray(resp);
                    if(dataArray.length()==1)
                    {
                        JSONObject temp=dataArray.getJSONObject(0);
                        String tempDate=temp.getString("date").substring(0,10);
                        String tempTime=temp.getString("time");
                        if(strings[2].equals(tempDate)&&(strings[3]+":00").equals(tempTime))
                        {
                            Log.d("Alarm",resp+"Yeahmaaannn");
                            approval="true";
                        }
                    }

                    String url="http://pro-scheduler-backend.herokuapp.com/deleteReminder/uid/"+strings[1]+"/rid/"+strings[7];
                    try{
                        String resp1 = Jsoup.connect(url).ignoreContentType(true).execute().body();
                    }
                    catch (Exception e)
                    {
                        Log.d("Error","Error");
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                    Log.d("Login",e.getMessage());
                }
                if(approval.equals("true"))
                {

                }

            }
            catch (Exception e)
            {
                Log.e("Error",e.toString());
            }
            return approval;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("true"))
            {
                Log.d("Alarm","Notification Will come");

                NotificationManager mNotificationManager;

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(getApplicationContext(), "Reminder");
                Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.bigText("Reminder");
                bigText.setBigContentTitle(title);
                bigText.setSummaryText(note);

                builder.setContentIntent(pendingIntent);
                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setContentTitle(title);
                builder.setContentText(note);

                builder.setPriority(Notification.PRIORITY_MAX);
                builder.setStyle(bigText);

                mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                int notipri=NotificationManager.IMPORTANCE_DEFAULT;
                if(priority.equals("H"))
                {
                    notipri=NotificationManager.IMPORTANCE_HIGH;
                }
                else if(priority.equals("M"))
                {
                    notipri=NotificationManager.IMPORTANCE_DEFAULT;
                }
                else if (priority.equals("L"))
                {
                    notipri=NotificationManager.IMPORTANCE_LOW;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    String channelId = "Reminder";
                    NotificationChannel channel = new NotificationChannel(
                            channelId,
                            "Reminder Channel",
                            notipri);
                    mNotificationManager.createNotificationChannel(channel);
                    builder.setChannelId(channelId);
                }
                // Will display the notification in the notification bar
                if(notification.equals("1")){
                mNotificationManager.notify(1, builder.build());
                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.beep);
                mediaPlayer.start();}


            }
        }
    }
}
