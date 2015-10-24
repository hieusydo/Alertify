package presto.watch.prestosampleproject.DefaultActivities;

import android.animation.AnimatorSet;
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
 * Created by davidjay on 8/4/15.
 */
public class DefaultWindowedGestureActivity extends AbstractGestureClientActivity {
    private GestureIndicator gestureIndicator;
    private String TAG = "DefaultGestureActivity";
    private Vibrator vibrator;

    @Override
    public ArrayList<GestureConstants.SubscriptionGesture> getGestureSubscpitionList() {
        ArrayList<GestureConstants.SubscriptionGesture> gestures = new ArrayList<GestureConstants.SubscriptionGesture>();
        gestures.add(GestureConstants.SubscriptionGesture.FLICK);
        gestures.add(GestureConstants.SubscriptionGesture.SNAP);
        gestures.add(GestureConstants.SubscriptionGesture.TWIST);
        gestures.add(GestureConstants.SubscriptionGesture.TILT);
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

        //Detect gestures, within a gesture window.
        //This value defaults to true, the function is included here for reference.
        setSubscribeWindowEvents(true);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        gestureIndicator = (GestureIndicator) findViewById(R.id.gestureIndicator);
    }


    @Override
    public void onSnap() {
        AnimatorSet snapAnim = new AnimatorSet();
        snapAnim.playSequentially(
                gestureIndicator.gestureNotif(getString(R.string.snap)),
                gestureIndicator.shrink()
        );
        snapAnim.start();
        vibrator.vibrate(350);
    }

    @Override
    public void onFlick() {
        AnimatorSet flickAnim = new AnimatorSet();
        flickAnim.playSequentially(
                gestureIndicator.gestureNotif(getString(R.string.flick)),
                gestureIndicator.shrink()
        );
        flickAnim.start();
        vibrator.vibrate(350);
    }

    @Override
    public void onTwist() {
        if (gestureIndicator.isExpanded()) {
            gestureIndicator.gestureNotif(getString(R.string.twist)).start();
            vibrator.vibrate(350);
        } else {
            gestureIndicator.expand().start();
            vibrator.vibrate(new long[] {0,80,80,80,250,40,40,40},-1);
        }
    }

    @Override
    public void onTiltX(float tiltX) {
        Log.e(TAG, "Function onTilt was not subscribed. Override if you changed subscriptions");
    }

    @Override
    public void onTilt(float x, float y, float z) {
        vibrator.vibrate(50);
    }

    @Override
    public void onGestureWindowClosed() {
        gestureIndicator.shrink().start();
        vibrator.vibrate(new long[] {0,40,40,40,250,80,80,80},-1);
    }

}
