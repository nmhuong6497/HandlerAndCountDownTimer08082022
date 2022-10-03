package com.huong.handlesandcountdowntimer08082022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    ImageView imageView;
    Button btnStart, btnPause;
    int[] arrImages = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5
    };
    int count = 1;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_view_banner);
        btnStart = findViewById(R.id.button_start);
        btnPause = findViewById(R.id.button_pause);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {
                    isRunning = true;

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Pause
                            if (!isRunning) {
                                handler.removeCallbacks(this);

                            } else {
                                // Run
                                count = count == arrImages.length ? 0 : count;
                                imageView.setImageResource(arrImages[count++]);
                                handler.postDelayed(this, 1000);
                            }
                        }
                    }, 1000);
                } else {
                    Toast.makeText(MainActivity.this, "Banner đang chạy", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Requirement: Nếu đang chạy thì dừng, không thì thông báo
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRunning) {
                    isRunning = false;
                } else {
                    Toast.makeText(MainActivity.this, "Banner đã dừng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

