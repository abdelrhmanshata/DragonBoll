package com.shata.dragonboll;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error->" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                super.run();
            }
        };
        thread.start();
    }
}
