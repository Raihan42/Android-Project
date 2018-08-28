package com.example.raihanrajon.in_safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Wellcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        Thread thread = new Thread()
        {

            @Override
            public void run() {


                try {

                    sleep(2500);

                }
                catch (Exception e)

                {
                    e.printStackTrace();
                }

                finally {

                    Intent intent = new Intent(Wellcome.this,MainActivity.class);
                    startActivity(intent);

                }

            }
        };

        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
