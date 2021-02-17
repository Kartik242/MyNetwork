package ru.ifsoft.network;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static ru.ifsoft.network.app.App.TAG;

public abstract class DoubleClickListener implements View.OnClickListener {

    private static final long DOUBLE_CLICK_TIME_DELTA = 1000;//milliseconds

    long secondClickTime = 0;


    @Override
    public void onClick(View v) {

        long clickTime = System.currentTimeMillis();
        if (clickTime - secondClickTime < DOUBLE_CLICK_TIME_DELTA) {
            onDoubleClick(v);
        }
        else
        {
            onSingleClick(v);
        }
        secondClickTime = clickTime;


    }


    public abstract void onSingleClick(View v);
    public abstract void onDoubleClick(View v);

}
