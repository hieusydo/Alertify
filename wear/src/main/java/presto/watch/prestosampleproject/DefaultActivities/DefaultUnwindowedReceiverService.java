package presto.watch.prestosampleproject.DefaultActivities;

import watch.nudge.gesturelibrary.AppControllerReceiverService;

/**
 * Created by davidjay on 8/7/15.
 */
public class DefaultUnwindowedReceiverService extends AppControllerReceiverService {
    @Override
    protected Class getWatchActivityClass() {
        return DefaultUnwindowedReceiverService.class;
    }
}
