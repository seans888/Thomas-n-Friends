package llantosthom.touchgestures;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import java.lang.Math;

import static android.view.GestureDetector.*;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity implements
        OnGestureListener,OnDoubleTapListener{

    //private float prevX;
    //private float prevY;

    //private float rangeX = 25f;
    //private float rangeY = 25f;

    //float distance = 0;


    float radius = 25f;
    float x, y, sX, sY, fX, fY;

    boolean singleTap;
    boolean doubleTap;
    boolean longPress;
    boolean scroll;
    boolean fling;

    int tapCount = 0;

    long upTime = 0;

    private static TextView textView;
    private GestureDetectorCompat GestureDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        fling = false;

        textView = (TextView)findViewById(R.id.textView);
        GestureDetect = new GestureDetectorCompat(this,this);
        GestureDetect.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);

        if(tapCount >= 2)
            tapCount = 0;

        x = event.getX();
        y = event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                upTime = event.getEventTime();
                tapCount ++;
                break;
        }

        float touchDuration = upTime - event.getDownTime();

        // TODO Get number of touches within a given time period
        // SINGLE TAP
        if(touchDuration < 500)
        {
            singleTap = true;
            longPress = false;
            doubleTap = false;
        }

        // DOUBLE TAPss
        if(tapCount == 2)
        {
            singleTap = false;
            longPress = false;
            doubleTap = true;
        }

        // LONG PRESS
        if ( event.getEventTime() - event.getDownTime() >= 500)
        {
            singleTap = false;
            doubleTap = false;
            tapCount = 0;
            float downPos = (float) Math.sqrt( pow(sX, 2) + pow(sY, 2) );
            float curPos = (float) Math.sqrt( pow(x, 2) + pow(y, 2) );

            if( Math.abs( curPos - downPos ) > radius )
            {
                longPress = false;
            }
            else
                longPress = true;
        }

        textView.setText(
                "\n\nON TOUCHEVENT"
                        +"\nSINGLE TAP: " + singleTap
                        +"\nDOUBLE TAP: " + doubleTap
                        +"\nLONG PRESS: " + longPress
                        +"\nCount: " + tapCount
                        +"\nCurrent X: " + x
                        +"\nCurrent Y: " + y
                        +"\nDown X: " + sX
                        +"\nDownY: " + sY
                        +"\nUp X: " + fX
                        +"\nUp Y: " + fY
                        +"\nDown Time: " + event.getDownTime()
                        +"\nEvent Time: " + event.getEventTime()
                        +"\nupTime: " + upTime
                        +"\ngetPointerCount: " + event.getPointerCount()

        );

        return super.onTouchEvent(event);
    }

































    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
       // textView.setText("\nonSingleTapConfirmed\n" + "X Position: " + e.getX() + "\nY Position: " + e.getY() + "\nEventTime: "
      //          + e.getEventTime() + "\nDownTime: " +e.getDownTime());
         return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //textView.setText("\nonDoubleTap\n" + "X Position: " + e.getX() + "\nY Position: " + e.getY() + "\nEventTime: "
               // + e.getEventTime() + "\nDownTime: " +e.getDownTime()  +"\nEventDuration: " + (e.getEventTime() - e.getDownTime()) );
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

       /* long EventDuration = e.getEventTime() - e.getDownTime();
        boolean doubleTapped = false;

        if(EventDuration > 100)
        {
            return false;
        }

       if(( prevX - e.getX() ) <= rangeX)
        {
            distance = prevX - e.getX();
            doubleTapped = true;
        }

        if((e.getX() - prevX) <= rangeX)
        {
            distance = e.getX() - prevX;
            doubleTapped = true;
        }


        */

        /* 8distance = (float)sqrt(  pow((double)prevX - (double)e.getX(), 2) + pow( (double)prevY - (double)e.getY(), 2)   );

        if(distance <= radius)
        {
            doubleTapped = true;
        }
        */
        /*
        if(doubleTapped == false)
        {
            return false;
        }
            textView.setText("\n\nonDoubleTapEvent\n"
                    + "X Position: " + e.getX()
                    + "\nY Position: " + e.getY()
                    + "\nDistance: " + distance
                    + "\n\nEventTime: " + e.getEventTime()
                    + "\nDownTime: " + e.getDownTime()
                    + "\nEventDuration: " + EventDuration
            );

        prevX = e.getX();
        prevY = e.getY();

        */

        return false;
    }



    @Override
    public boolean onDown(MotionEvent e) {
        //textView.setText("\nonDown" + e.toString());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //textView.setText("\nonShowPress" + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //textView.setText("\nonSingleTapUp" + e.toString());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
       // textView.setText("\nonScroll" + e1.toString()+ e2.toString());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

/*

        textView.setText("\n\n\nonLongPress"

                + "\nX Position: " + e.getX()
                + "\nY Position: " + e.getY()
                + "\nEventTime: " + e.getEventTime()
                + "\nDownTime: " +e.getDownTime());

*/

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
       // textView.setText("\nonFling" + e1.toString() + e2.toString());
        return false;
    }



}
