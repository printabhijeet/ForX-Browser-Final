package com.zimax.forxbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText urlET = findViewById(R.id.urlET);

        final LinearLayout instagramBtn = findViewById(R.id.instagramBtn);
        final LinearLayout facebookBtn = findViewById(R.id.facebookBtn);
        final LinearLayout linkedinBtn = findViewById(R.id.linkedinBtn);
        final LinearLayout pinterestBtn = findViewById(R.id.pinterestBtn);
        final LinearLayout twitterBtn = findViewById(R.id.twitterBtn);
        final LinearLayout youtubeBtn = findViewById(R.id.youtubeBtn);

        urlET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    final String urlTxt = urlET.getText().toString();

                    if (!urlTxt.isEmpty()) {

                        urlET.setText("");
                        Intent intent = new Intent(MainActivity.this, BrowserView.class);
                        intent.putExtra("url", urlTxt);

                        startActivity(intent);
                    }
                }
                return false;
            }
        });

        instagramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.instagram.com");

                startActivity(intent);

            }
        });

        facebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.facebook.com");

                startActivity(intent);

            }
        });

        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.linkedin.com");

                startActivity(intent);

            }
        });

        pinterestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.pinterest.com");

                startActivity(intent);

            }
        });

        twitterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.twitter.com");

                startActivity(intent);

            }
        });

        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BrowserView.class);
                intent.putExtra("url", "www.youtube.com");

                startActivity(intent);

            }
        });
    }
}