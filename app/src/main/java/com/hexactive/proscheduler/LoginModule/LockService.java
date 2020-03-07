package com.hexactive.proscheduler.LoginModule;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class LockService extends Service {
    public LockService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocKService","Locked");
        SharedPreferences sp = getSharedPreferences("mycredentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("allow",true);
        edit.commit();
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
