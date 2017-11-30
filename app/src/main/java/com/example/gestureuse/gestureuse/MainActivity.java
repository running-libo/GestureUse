package com.example.gestureuse.gestureuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private GestureFrame gestureFrame;
    private StringBuilder stringBuilder;
    private TextView tvMsg;
    private FocusBoxView ivClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureFrame = (GestureFrame) findViewById(R.id.gesture_frame);
        textView = (TextView) findViewById(R.id.tv);
        tvMsg = (TextView) findViewById(R.id.tv_Msg);
        ivClick = (FocusBoxView) findViewById(R.id.iv_click);

        stringBuilder = new StringBuilder();
        gestureFrame.setCallback(new GestureFrame.IcallBack() {
            @Override
            public void callBack(String string) {
                stringBuilder.append(string + "   ");
                textView.setText(stringBuilder.toString());
            }

            @Override
            public void callBackScale(String scale) {
                tvMsg.setText(scale);
            }

            @Override
            public void leftEvent() {
                TranslateAnimation animation = new TranslateAnimation(0,-300,0,0);
                animation.setDuration(300);
                tvMsg.startAnimation(animation);
                tvMsg.setText("左滑");
            }

            @Override
            public void rightEvent() {
                TranslateAnimation animation = new TranslateAnimation(0,300,0,0);
                animation.setDuration(300);
                tvMsg.startAnimation(animation);
                tvMsg.setText("右滑");
            }

            @Override
            public void click(float x, float y) {
                ivClick.startFocus(x,y);
            }
        });

    }

}
