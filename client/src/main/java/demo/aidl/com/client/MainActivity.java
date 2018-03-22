package demo.aidl.com.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aidl.demo.IUserAidlInterface;
import com.aidl.demo.UserBean;

public class MainActivity extends AppCompatActivity {

    private TextView loginTv;
    private TextView tokenTv;
    private TextView contentTv;
    private TextView userInfoTv;
    private IUserAidlInterface anInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginTv = (TextView) findViewById(R.id.tv_login);
        contentTv = (TextView) findViewById(R.id.tv_content);
        tokenTv = (TextView) findViewById(R.id.tv_token);
        userInfoTv = (TextView) findViewById(R.id.tv_get_user);
        initService();
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    anInterface.login("18757110824","123456");
                } catch (RemoteException e) {

                }
            }
        });
        tokenTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String token = anInterface.getToken();
                    contentTv.setText(token);
                } catch (RemoteException e) {
                }
            }
        });
        userInfoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean bean = null;
                try {
                    bean = anInterface.getUser();
                    Log.e("main",bean.getId()+":"+bean.getName());
                } catch (RemoteException e) {

                }
            }
        });
    }

    private void initService() {
        Intent intent = new Intent();
//        intent.setAction("com.aidl.demo");
//        intent.setPackage("com.aidl.demo");
        intent.setClassName("com.aidl.demo","com.aidl.demo.UserService");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MainActivity", "service connected");
            anInterface = IUserAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
