package com.controlcenter.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;

public class OverlayService extends Service {

    private WindowManager wm;
    private View trigger;
    private WebView webView;
    private boolean isOpen = false;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Floating trigger button
        Button btn = new Button(this);
        btn.setText("≡");
        trigger = btn;

        WindowManager.LayoutParams triggerParams = new WindowManager.LayoutParams(
                150, 150,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        triggerParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

        wm.addView(trigger, triggerParams);

        // WebView panel
        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        webView.loadUrl("file:///android_asset/public/index.html");

        WindowManager.LayoutParams panelParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );

        panelParams.gravity = Gravity.TOP;

        btn.setOnClickListener(v -> {
            if (!isOpen) {
                wm.addView(webView, panelParams);
                isOpen = true;
            } else {
                wm.removeView(webView);
                isOpen = false;
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
