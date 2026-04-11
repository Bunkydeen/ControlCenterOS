package com.controlcenter.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.*;
import android.widget.*;

public class OverlayService extends Service {

    private WindowManager wm;
    private View trigger;
    private View panel;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        // trigger button
        trigger = new Button(this);
        ((Button) trigger).setText("≡");

        WindowManager.LayoutParams triggerParams = new WindowManager.LayoutParams(
                150, 150,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        triggerParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;

        wm.addView(trigger, triggerParams);

        // panel
        panel = new LinearLayout(this);
        panel.setBackgroundColor(0xCC111111);

        WindowManager.LayoutParams panelParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                600,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        panelParams.gravity = Gravity.TOP;

        trigger.setOnClickListener(v -> {
            try {
                wm.addView(panel, panelParams);
            } catch (Exception ignored) {}
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
