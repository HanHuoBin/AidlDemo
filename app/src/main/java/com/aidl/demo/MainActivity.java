package com.aidl.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements UserService.UserLoginListener {

    private TextView contentTv;
    IUserAidlInterface userService;
    UserService  service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentTv = (TextView) findViewById(R.id.tv_content);
        initService();
    }

    private void initService() {
        service = new UserService();
        Intent intent = new Intent(this,service.getClass());
//        intent.setClassName("com.aidl.demo", UserService.class.getName());
        bindService(intent,connection , Context.BIND_AUTO_CREATE);
        service.setListener(this);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MainActivity", "service connected");
            userService = IUserAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            userService = null;
        }
    };

    @Override
    public void login(String mobile, String password) {
        Log.e("main",mobile+"::"+password);
        contentTv.setText(mobile + "---" + password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
