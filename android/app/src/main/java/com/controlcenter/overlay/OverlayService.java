package com.controlcenter.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.FrameLayout;

public class OverlayService extends Service {

    private WindowManager wm;
    private FrameLayout container;
    private WebView webView;
    private View handle;

    private boolean isOpen = false;
    private float startY = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        // MAIN CONTAINER
        container = new FrameLayout(this);
        container.setBackgroundColor(0xCC000000);

        // WEBVIEW (your UI)
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.loadUrl("file:///android_asset/public/index.html");

        // HANDLE (top drag bar)
        handle = new View(this);
        handle.setBackgroundColor(0x66FFFFFF);

        FrameLayout.LayoutParams handleParams = new FrameLayout.LayoutParams(
                200, 20
        );
        handleParams.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        container.addView(webView);
        container.addView(handle, handleParams);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP;

        // START HIDDEN ABOVE SCREEN
        container.setTranslationY(-2000);

        wm.addView(container, params);

        // HANDLE DRAG LOGIC
        handle.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    startY = event.getRawY();
                    return true;

                case MotionEvent.ACTION_MOVE:
                    float dy = event.getRawY() - startY;
                    if (dy > 0) container.setTranslationY(-2000 + dy);
                    return true;

                case MotionEvent.ACTION_UP:
                    if (container.getTranslationY() > -1000) {
                        open();
                    } else {
                        close();
                    }
                    return true;
            }
            return false;
        });

        // SWIPE ANYWHERE TO CLOSE
        webView.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    startY = event.getRawY();
                    return false;

                case MotionEvent.ACTION_MOVE:
                    float dy = event.getRawY() - startY;
                    if (dy < 0) {
                        container.setTranslationY(dy);
                    }
                    return false;

                case MotionEvent.ACTION_UP:
                    if (container.getTranslationY() < -300) {
                        close();
                    } else {
                        open();
                    }
                    return false;
            }
        });
    }

    private void open() {
        container.animate().translationY(0).setDuration(250).start();
        isOpen = true;
    }

    private void close() {
        container.animate().translationY(-2000).setDuration(250).start();
        isOpen = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
