package presto.watch.prestosampleproject.DefaultActivities;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;

import presto.watch.prestosampleproject.R;
import watch.nudge.gesturelibrary.AbstractGestureClientActivity;
import watch.nudge.gesturelibrary.GestureConstants;
import watch.nudge.gesturelibrary.UI.GestureIndicator;

/**
 * Created by davidjay on 7/31/15.
 */
public class DefaultUnwindowedGestureActivity extends AbstractGestureClientActivity {
    private GestureIndicator gestureIndicator;
    private String TAG = "DefaultGestureActivity";
    private Vibrator vibrator;

    @Override
    public ArrayList<GestureConstants.SubscriptionGesture> getGestureSubscpitionList() {
        ArrayList<GestureConstants.SubscriptionGesture> gestures = new ArrayList<GestureConstants.SubscriptionGesture>();
        gestures.add(GestureConstants.SubscriptionGesture.FLICK);
        gestures.add(GestureConstants.SubscriptionGesture.SNAP);
        gestures.add(GestureConstants.SubscriptionGesture.TWIST);
        return gestures;
    }

    @Override
    public boolean sendsGestureToPhone() {
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Keep the screen on.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Detect all gestures, not just those that take place in a gesture window.
        setSubscribeWindowEvents(false);

        gestureIndicator = (GestureIndicator) findViewById(R.id.gestureIndicator);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        gestureIndicator.expand().start();
    }


    @Override
    public void onSnap() {
        gestureIndicator.gestureNotif(getString(R.string.snap)).start();
        vibrator.vibrate(350);
    }

    @Override
    public void onFlick() {
        gestureIndicator.gestureNotif(getString(R.string.flick)).start();
        vibrator.vibrate(350);
    }

    @Override
    public void onTwist() {
        gestureIndicator.gestureNotif(getString(R.string.twist)).start();
        vibrator.vibrate(350);
    }

    @Override
    public void onTiltX(float tiltX) {
        Log.e(TAG, "Function onTiltX was not subscribed. Override if you changed subscriptions");
    }

    @Override
    public void onTilt(float tiltX, float tiltY, float tiltZ) {
        Log.e(TAG, "Function onTilt was not subscribed. Override if you changed subscriptions");
    }

    @Override
    public void onGestureWindowClosed() {
        Log.e(TAG, "Function onGestureWindowClosed was not subscribed. Override if you changed subscriptions");
    }

}
