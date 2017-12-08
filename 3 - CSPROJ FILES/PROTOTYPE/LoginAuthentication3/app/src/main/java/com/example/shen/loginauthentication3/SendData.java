package com.example.shen.loginauthentication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.GestureDetector.OnGestureListener;
import static android.view.GestureDetector.OnDoubleTapListener;

import static java.lang.Math.pow;

public class SendData extends AppCompatActivity implements OnGestureListener, OnDoubleTapListener{
    // GENERAL VARIABLES
    private static TextView textView;
    private GestureDetectorCompat GestureDetect;
    String myName;
    String deviceName = android.os.Build.MODEL;
    String deviceMan = android.os.Build.MANUFACTURER;

    // GESTURE DETAILS VARIABLES
    float radius = 25f;
    float x, y, sX, sY, fX, fY;
    int tapCount = 0;
    long upTime = 0;
    long totalTime;
    float prevX = 0;
    float prevY = 0;

    // GESTURE RECOGNITION VARIABLES
    boolean singleTap;
    boolean doubleTap;
    boolean longPress;
    boolean swipe;
    boolean scroll;

    // LAYOUT VARIABLES
    EditText etVelocity;
    TextView username;
    Button submitData;

    // FIREBASE VARIABLES
    private FirebaseAuth mAuth;
    DatabaseReference databaseVelocity;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_data);

        // GESTURE DETAILS VARIABLES
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        totalTime = 0;

        // GESTURE RECOGNITION VARIABLES
        singleTap = false;
        doubleTap = false;
        longPress = false;
        scroll = false;
        swipe = false;

        // FIREBASE VARIABLES
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String databaseUserUrl = "https://fir-loginapp-c45e0.firebaseio.com/Attempt Details/" + user.getUid() + "/details";
        String databaseVelocityUrl = "https://fir-loginapp-c45e0.firebaseio.com/Attempt Details/" + user.getUid() + "/gesture";

        username = (TextView)findViewById(R.id.tvName1);
        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), Authentication.class));
        }

        if(user != null){
            username.setText("Welcome, " + user.getDisplayName() + "\n User Id: " + user.getUid());
            myName = user.getDisplayName().toString();
        }

        databaseVelocity = FirebaseDatabase.getInstance().getReferenceFromUrl(databaseVelocityUrl);
        databaseUser = FirebaseDatabase.getInstance().getReferenceFromUrl(databaseUserUrl);

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        textView = (TextView)findViewById(R.id.textView2);
        totalTime = event.getDownTime() - upTime;
        x = event.getX();
        y = event.getY();
        float touchDuration = upTime - event.getDownTime();
        long eventDuration = event.getEventTime() - event.getDownTime();

        if (tapCount >= 2){
            tapCount = 0;
        }

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

        //SINGLE TAP
        singleTapFunc(touchDuration);

        //DOUBLE TAP
        float tapPos = (float) Math.sqrt(pow(prevX - event.getX(), 2) + pow(prevY - event.getY(), 2));
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
                        + "\nDown Time: " + event.getDownTime() + "ms"
                        + "\nEvent Time: " + event.getEventTime() + "ms"
                        + "\nupTime: " + upTime + "ms"
                        + "\nEvent Duration: " + totalTime + "ms"
                        + "\ngetPointerCount: " + event.getPointerCount()
                        + "\nDevice Name: " + deviceName
                        + "\nDevice Manufacturer: " + deviceMan
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

    private void addVelocity(){
        String id = databaseVelocity.push().getKey();
        String tapGestureId =  databaseUser.push().getKey();    //user.getUid();

        Velocity velocity = new Velocity(id, singleTap, doubleTap, longPress);
        GestureDetails gestureDetails = new GestureDetails(x, y, sX, sY, fX, fY, totalTime, myName, deviceName, deviceMan);

        databaseVelocity.child(id).setValue(velocity);
        databaseUser.child(tapGestureId).setValue(gestureDetails);
        Toast.makeText(this, "Value Added", Toast.LENGTH_LONG).show();
    }
}
