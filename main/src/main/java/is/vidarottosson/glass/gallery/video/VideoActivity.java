package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import android.os.Handler;

import is.vidarottosson.glass.gallery.DeleteActivity;
import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class VideoActivity extends Activity implements GestureDetector.BaseListener {
	public static final String TAG = VideoActivity.class.getSimpleName();

	public static final int INTENT_OPTIONS_MENU = 101;

	private VideoItem mVideo;

	private VideoView mVideoView;
	private TextView mTxtView;
    private ImageView mImageView;

	private Handler mHandler = new Handler();

    private Animation mAnimation;
	private GestureDetector mDetector;

	//     _     _  __                      _
	//    | |   (_)/ _| ___  ___ _   _  ___| | ___
	//    | |   | | |_ / _ \/ __| | | |/ __| |/ _ \
	//    | |___| |  _|  __/ (__| |_| | (__| |  __/
	//    |_____|_|_|  \___|\___|\__, |\___|_|\___|
	//                           |___/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			mVideo = (VideoItem) extras.get(VideoItem.KEY_FOR_INTENT_EXTRA);
		} else {
			Toast.makeText(this, "No video.", Toast.LENGTH_SHORT).show();
			finish();
		}

		mVideoView = (VideoView) findViewById(R.id.video_videoView);
		mVideoView.setVideoPath(mVideo.getPath());

        mTxtView = (TextView) findViewById(R.id.video_textView);
        mImageView = (ImageView) findViewById(R.id.video_imgView);

		mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mVideoView.start();
			}
		});

        mAnimation = AnimationUtils.loadAnimation(this, R.anim.expand_and_fade);
		mDetector = new GestureDetector(this).setBaseListener(this);

        mHandler.postDelayed(mRunner, 200);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == INTENT_OPTIONS_MENU && resultCode == DeleteActivity.RESULT_DELETED) {
			setResult(DeleteActivity.RESULT_DELETED);
			finish();
		}
	}

	//     ___       _             __                  ___                 _                           _        _   _
	//    |_ _|_ __ | |_ ___ _ __ / _| __ _  ___ ___  |_ _|_ __ ___  _ __ | | ___ _ __ ___   ___ _ __ | |_ __ _| |_(_) ___  _ __  ___
	//     | || '_ \| __/ _ \ '__| |_ / _` |/ __/ _ \  | || '_ ` _ \| '_ \| |/ _ \ '_ ` _ \ / _ \ '_ \| __/ _` | __| |/ _ \| '_ \/ __|
	//     | || | | | ||  __/ |  |  _| (_| | (_|  __/  | || | | | | | |_) | |  __/ | | | | |  __/ | | | || (_| | |_| | (_) | | | \__ \
	//    |___|_| |_|\__\___|_|  |_|  \__,_|\___\___| |___|_| |_| |_| .__/|_|\___|_| |_| |_|\___|_| |_|\__\__,_|\__|_|\___/|_| |_|___/
	//                                                              |_|

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		return mDetector.onMotionEvent(event);
	}

	@Override
	public boolean onGesture(Gesture gesture) {
		Log.i(TAG, "the gesture is: " + gesture);
		if (gesture == Gesture.TAP || gesture == Gesture.LONG_PRESS) {
			togglePlaying();
			return true;
		}

		return false;
	}

	//     ____       _            _         __  __      _   _               _
	//    |  _ \ _ __(_)_   ____ _| |_ ___  |  \/  | ___| |_| |__   ___   __| |___
	//    | |_) | '__| \ \ / / _` | __/ _ \ | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|
	//    |  __/| |  | |\ V / (_| | ||  __/ | |  | |  __/ |_| | | | (_) | (_| \__ \
	//    |_|   |_|  |_| \_/ \__,_|\__\___| |_|  |_|\___|\__|_| |_|\___/ \__,_|___/

	private void togglePlaying() {
		if (mVideoView.isPlaying()) {
			mVideoView.pause();
            mHandler.removeCallbacks(mRunner);

            mImageView.setImageResource(R.drawable.ic_pause_large);
		} else {
			mVideoView.start();
            mHandler.postDelayed(mRunner, 200);

            mImageView.setImageResource(R.drawable.ic_play_large);
		}

        mImageView.setVisibility(View.VISIBLE);
        mImageView.startAnimation(mAnimation);
        mImageView.setVisibility(View.INVISIBLE);
	}

    private Runnable mRunner = new Runnable() {
        @Override
        public void run() {
            mTxtView.setText(Utility.videoProgressTextFromMillis(mVideoView.getCurrentPosition(), mVideoView.getDuration()));

            mHandler.postDelayed(this, 200);
        }
    };
}