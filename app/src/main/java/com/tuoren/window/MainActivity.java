package com.tuoren.window;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

public class MainActivity extends AppCompatActivity {

    private SlideView slideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slideView == null) {
                    slideView = new SlideView(getBaseContext());
                    slideView.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
                    WindowManager windowManager = getWindowManager();
                    WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                    params.width = 150;
                    params.height = 150;
                    params.x = 100;
                    params.format = PixelFormat.RGBA_8888;
                    params.gravity = Gravity.START;
                    params.flags = FLAG_NOT_FOCUSABLE;
                    params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                    windowManager.addView(slideView, params);
                }
                Toast.makeText(getBaseContext(), "Show Window", Toast.LENGTH_LONG).show();
            }
        });
    }
}