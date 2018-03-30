package com.aidl.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hanbin on 2018/3/21.
 */

public class UserService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IUserAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                                   double aDouble, String aString)
                    throws RemoteException {

            }

            @Override
            public boolean login(String mobile, String password) throws RemoteException {
                Log.e("main",mobile+":"+password);
                return false;
            }

            @Override
            public String getToken() throws RemoteException {
                String token = (int)((Math.random()*9+1)*100000) + "";
                Log.e("main",token);
                return token;
            }

            @Override
            public UserBean getUser() throws RemoteException {
                UserBean bean = new UserBean();
                bean.setId(111);
                bean.setName("hanbin");
                return bean;
            }
        };
    }

}
