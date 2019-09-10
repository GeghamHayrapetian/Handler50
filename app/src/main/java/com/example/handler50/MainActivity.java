package com.example.handler50;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private Switch aSwitch;
    private Button start;
    private Button stop;
    private static final String TAG = "MainActivity";
    private Thread thread;
    private Handler handler;
    private volatile boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        start = findViewById(R.id.b_start);
        stop = findViewById(R.id.b_stop);
        status=false;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ++i) {
                    if (status)
                        return;
                    if (i == 5) {
                        handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                start.setText("50%");

                            }
                        });
                    }
                    Log.d(TAG, "StartClick :" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        thread.start();
    }
});
stop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        status=true;
    }
});
    }

}
