package com.example.gestureuse.gestureuse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.RelativeLayout;

/**
 * Created by libo on 2017/11/30.
 */

public class GestureFrame extends RelativeLayout{
    private GestureDetector gestureDetector;
    private ScaleGestureDetector scaleGestureDetector;
    private IcallBack callBack;
    private float startX;
    private float endX;
    private int curHeight;

    public GestureFrame(Context context) {
        super(context);
        init();
    }

    public GestureFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(),new MyGestureDetectorListener());
        scaleGestureDetector = new ScaleGestureDetector(getContext(),new MyScaleListener());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                callBack.callBack("按下");
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                callBack.callBack("抬起");

                if(endX - startX > 200){
                    callBack.rightEvent();
                }else if(startX - endX > 200){
                    callBack.leftEvent();
                }
                break;
        }

        //将触摸事件传递给手势识别器处理
        gestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    class MyGestureDetectorListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            callBack.click(e.getX(),e.getY());
            callBack.callBack("单击");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            curHeight += distanceY;
            callBack.callBackScale("上下滑动  " + curHeight);
            //distanceX 左右滑动值
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            callBack.callBack("长按");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    class MyScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            callBack.callBackScale("缩放比例： " + detector.getScaleFactor());
            return super.onScale(detector);
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
        }

    }

    public interface IcallBack{
        void callBack(String string);
        void callBackScale(String scale);
        void leftEvent();
        void rightEvent();
        void click(float x,float y);
    }

    public void setCallback(IcallBack callback){
        this.callBack = callback;
    }

}
