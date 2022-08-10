package com.example.imageurl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.pharm.or.kr:442/images/sb_photo/big3/A11A4380A006101.jpg");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream inputStream = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);

                } catch ( MalformedURLException e ) {
                    e.printStackTrace();
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        };
        mThread.start();

        try {
            mThread.join();
            imageView.setImageBitmap(bitmap);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

    }
}