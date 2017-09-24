package llantosthom.touchgestures;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.gesture.Gesture;
import android.widget.TextView;

import static android.view.GestureDetector.*;


public class MainActivity extends AppCompatActivity implements
        OnGestureListener,OnDoubleTapListener{

    private float prevX;
    private float prevY;

    private float rangeX = 25f;
    private float rangeY = 25f;

    float distance = 0;

    private static TextView textView;
    private GestureDetectorCompat GestureDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        GestureDetect = new GestureDetectorCompat(this,this);
        GestureDetect.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        GestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        textView.setText("\nonSingleTapConfirmed\n" + "X Position: " + e.getX() + "\nY Position: " + e.getY() + "\nEventTime: "
                + e.getEventTime() + "\nDownTime: " +e.getDownTime());
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

        long EventDuration = e.getEventTime() - e.getDownTime();
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


        /* 8distance = (float)sqrt(  pow((double)prevX - (double)e.getX(), 2) + pow( (double)prevY - (double)e.getY(), 2)   );

        if(distance <= radius)
        {
            doubleTapped = true;
        }
        */
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

        return false;
    }



    @Override
    public boolean onDown(MotionEvent e) {
        //textView.setText("\nonDown" + e.toString());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        textView.setText("\nonShowPress" + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //textView.setText("\nonSingleTapUp" + e.toString());
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        textView.setText("\nonScroll" + e1.toString()+ e2.toString());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        textView.setText("\nonLongPress" + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        textView.setText("\nonFling" + e1.toString() + e2.toString());
        return false;
    }

}
