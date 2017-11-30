package com.example.gestureuse.gestureuse;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by libo on 2017/10/27.
 */

public class FocusBoxView extends AppCompatImageView {

    public FocusBoxView(Context context) {
        super(context);
    }

    public FocusBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startFocus(float x,float y){

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin = (int) (x - getMeasuredWidth() / 2);
        layoutParams.topMargin = (int) (y - getMeasuredHeight() / 2);
        setLayoutParams(layoutParams);
        setVisibility(VISIBLE);

        handler.sendEmptyMessageDelayed(0,500);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                setBackgroundResource(R.mipmap.circle_green);
                handler.sendEmptyMessageDelayed(1,500);
            }
        }
    };

}
