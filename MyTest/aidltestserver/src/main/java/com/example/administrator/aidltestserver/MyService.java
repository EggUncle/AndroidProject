package com.example.administrator.aidltestserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyService extends Service {

   class IMyService extends  IMyAidlInterface.Stub{

       @Override
       public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

       }

       @Override
       public int print() throws RemoteException {
           return 1;
       }
   }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return new IMyService();
    }
}
