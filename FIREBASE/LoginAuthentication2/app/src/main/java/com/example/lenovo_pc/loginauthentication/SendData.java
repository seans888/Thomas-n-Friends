package com.example.lenovo_pc.loginauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.GestureDetector.OnGestureListener;
import static android.view.GestureDetector.OnDoubleTapListener;

import static java.lang.Math.pow;

public class SendData extends AppCompatActivity implements OnGestureListener, OnDoubleTapListener{
    float radius = 25f;
    float x, y, sX, sY, fX, fY;

    boolean singleTap;
    boolean doubleTap;
    boolean longPress;
    boolean scroll;
    boolean fling;
    boolean swipe;

    int tapCount = 0;

    long upTime = 0;
    float prevX = 0;
    float prevY = 0;

    private static TextView textView;
    private GestureDetectorCompat GestureDetect;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        textView = (TextView)findViewById(R.id.textView2);

        if (tapCount >= 2){
            tapCount = 0;
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                upTime = event.getEventTime();
                tapCount++;
                if (tapCount == 1) {
                    prevX = event.getX();
                    prevY = event.getY();
                }
                break;
        }

        float touchDuration = upTime - event.getDownTime();

        //SINGLE TAP
        singleTapFunc(touchDuration);

        //DOUBLE TAP
        float tapPos = (float) Math.sqrt(pow(prevX - event.getX(), 2) + pow(prevY - event.getY(), 2));
        long eventDuration = event.getEventTime() - event.getDownTime();

        doubleTapFunc(tapPos);

        //LONGPRESS
        float longPressTime = (event.getEventTime() - event.getDownTime());
        longPressFunc(longPressTime);

        //SWIPE
        float swipeDistanceX = Math.abs(fX - sX);
        float swipeDistanceY = Math.abs(fY - sY);

        if( swipeDistanceX >= 200) {
            if (swipeDistanceY >= 200) {
                if (eventDuration > 100) {
                    singleTap = false;
                    doubleTap = false;
                    longPress = false;
                    scroll = false;
                    swipe = true;
                }
            }
        }

        textView.setText(
                "\n\nON TOUCHEVENT"
                        + "\nSINGLE TAP: " + singleTap
                        + "\nDOUBLE TAP: " + doubleTap
                        + "\nLONG PRESS: " + longPress
                        + "\nSWIPE: " + swipe
                        + "\nCount: " + tapCount
                        + "\nCurrent X: " + x
                        + "\nCurrent Y: " + y
                        + "\nDown X: " + sX
                        + "\nDownY: " + sY
                        + "\nUp X: " + fX
                        + "\nUp Y: " + fY
                        + "\nDown Time: " + event.getDownTime()
                        + "\nEvent Time: " + event.getEventTime()
                        + "\nupTime: " + upTime
                        + "\ngetPointerCount: " + event.getPointerCount()
        );
        return super.onTouchEvent(event);
    }

    public void singleTapFunc(float touchDuration) {
        if (touchDuration < 500) {
            singleTap = true;
            longPress = false;
            doubleTap = false;
            swipe = false;
        }
    }

    public void doubleTapFunc(float tapPos) {
        if (tapCount == 2) {
            if (tapPos <= radius) {
                singleTap = false;
                longPress = false;
                doubleTap = true;
                swipe = false;
            }
        }
    }

    public void longPressFunc(float longPressTime) {
        if (longPressTime >= 500) {
            singleTap = false;
            doubleTap = false;
            swipe = false;
            tapCount = 0;
            float downPos = (float) Math.sqrt(pow(sX, 2) + pow(sY, 2));
            float curPos = (float) Math.sqrt(pow(x, 2) + pow(y, 2));
            if (Math.abs(curPos - downPos) > radius) {
                longPress = false;
            }else{
                longPress = true;
            }
        }
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    EditText etVelocity;
    Button submitData;

    DatabaseReference databaseVelocity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data);

        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;

        singleTap = false;
        doubleTap = false;
        longPress = false;
        scroll = false;
        swipe = false;

        databaseVelocity = FirebaseDatabase.getInstance().getReference("velocity");
        etVelocity = (EditText) findViewById(R.id.etVelocity);
        submitData = (Button) findViewById(R.id.submitData);
        textView = (TextView) findViewById(R.id.textView);
        GestureDetect = new GestureDetectorCompat(this, this);
        GestureDetect.setOnDoubleTapListener(this);

        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addVelocity();
            }
        });
    }

    private void addVelocity(){
        String value = etVelocity.getText().toString().trim();
        if(!TextUtils.isEmpty(value)){
            String id = databaseVelocity.push().getKey();
            Velocity velocity = new Velocity(id, value);
            databaseVelocity.child(id).setValue(velocity);
            Toast.makeText(this, "Value Added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "ENTER VELOCITY", Toast.LENGTH_LONG).show();
        }
    }
}
