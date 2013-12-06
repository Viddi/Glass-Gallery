package com.vidarottosson.deviceexplorer;
//  Created by Viddi on 12/5/13.

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.TimelineManager;

public class DeviceExplorerService extends Service {

    public static final String TAG  = DeviceExplorerService.class.getSimpleName();
    public static final String LIVE_CARD_ID = "DeviceExplorer";

    private TimelineManager mTimelineManager;
    private LiveCard mLiveCard;

    @Override
    public void onCreate() {
        super.onCreate();

        mTimelineManager = TimelineManager.from(this);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mLiveCard == null) {
            Log.d(TAG, "Publishing LiveCard");
            mLiveCard = mTimelineManager.getLiveCard(LIVE_CARD_ID);

            mLiveCard.enableDirectRendering(true);
            mLiveCard.setNonSilent(true);

            Intent menuIntent = new Intent(this, MenuActivity.class);
            menuIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            mLiveCard.setAction(PendingIntent.getActivity(this, 0, menuIntent, 0));

            mLiveCard.publish();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(mLiveCard != null && mLiveCard.isPublished()) {
            mLiveCard.unpublish();
            mLiveCard = null;
        }



        super.onDestroy();
    }
}
