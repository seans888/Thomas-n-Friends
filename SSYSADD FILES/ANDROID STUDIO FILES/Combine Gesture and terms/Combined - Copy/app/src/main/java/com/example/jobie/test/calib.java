package com.example.jobie.test;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import static android.view.GestureDetector.*;


public class calib extends ActionBarActivity implements
        OnGestureListener,OnDoubleTapListener {

    private static TextView textView;
    private GestureDetectorCompat GestureDetect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calib);

        textView = (TextView)findViewById(R.id.textView6);
        GestureDetect = new GestureDetectorCompat(this, this);
        GestureDetect.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        textView.setText("Single Tap" + e.toString());
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        textView.setText("Double Tap" + e.toString());
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        textView.setText("Double Tap Event" + e.toString());
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        textView.setText("Down" + e.toString());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        textView.setText("Show Press" + e.toString());

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        textView.setText("Single Tap Up" + e.toString());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        textView.setText("Scroll" + e1.toString() + e2.toString());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        textView.setText("Long Press" + e.toString());


    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        textView.setText("Fling" + e1.toString() + e2.toString());
        return false;
    }
}
