package com.zimax.forxbrowser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BrowserView extends AppCompatActivity {

    private String url;
    private ProgressBar progressBar;

    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_view2);

        final WebView webview = findViewById(R.id.webView);
        final EditText urlET = findViewById(R.id.urlET);
        final ImageView homeBtn = findViewById(R.id.homeIcon);



        url = getIntent().getStringExtra("url");

        final String urlData = url.substring(0, 4);

        if(!urlData.contains("www")){
            url = "www.google.com/search?q="+url;
        }

        urlET.setText(url);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                urlET.setText(url);
            }
        });

        //Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);

                if(progress<100 && progressBar.getVisibility() == progressBar.GONE){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                if(progress == 100){
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });

        //BackButton
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;

                    switch(keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

        urlET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH){

                    final String urlTxt = urlET.getText().toString();

                    if(!urlTxt.isEmpty()){

                        final String urlData = urlTxt.substring(0, 4);

                        if(!urlData.contains("www")){
                            url = "www.google.com/search?q="+url;
                        }
                        else {
                            url = urlTxt;
                        }
                    }
                }
                return false;
            }
        });
        //download any thing
        webview.setDownloadListener(new DownloadListener() {

            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


