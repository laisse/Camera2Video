
package com.example.camerapreview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CameraSurfaceTextureActivity extends Activity implements OnClickListener {

    private static final String TAG = "CameraPreviewActivity";

    private Button mStart;
    private Button mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_surface_texture);
        init();
    }

    private void init() {
        mStart = (Button) this.findViewById(R.id.start);
        mStart.setClickable(true);
        mStart.setOnClickListener(this);
        mStart.setBackgroundColor(Color.BLUE);

        mStop = (Button) this.findViewById(R.id.stop);
        mStop.setOnClickListener(this);
        mStop.setClickable(false);
        mStop.setBackgroundColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        if (v == mStart) {
            startStreamer();
        } else if (v == mStop) {
            stopStreamer();
        }
    }

    private void startStreamer() {
        mStart.setClickable(false);
        mStop.setClickable(true);
        mStop.setBackgroundColor(Color.BLUE);
        mStart.setBackgroundColor(Color.RED);
        Thread openThread = new Thread() {
            @Override
            public void run() {
                CameraWrapper.getInstance().doOpenCamera();
            }
        };
        openThread.start();
    }

    private void stopStreamer() {
        CameraWrapper.getInstance().doStopCamera();
        mStart.setClickable(true);
        mStop.setClickable(false);
        mStart.setBackgroundColor(Color.BLUE);
        mStop.setBackgroundColor(Color.RED);
    }
}
