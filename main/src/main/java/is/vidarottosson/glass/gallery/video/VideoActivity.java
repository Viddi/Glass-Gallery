package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.TextureView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import is.vidarottosson.glass.gallery.R;
import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class VideoActivity extends Activity {
	public static final String TAG = VideoActivity.class.getSimpleName();

	private VideoItem mVideo;

	private VideoView mVideoView;
	private TextView mTxtView;
	private ProgressBar mProgressBar;

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
		mTxtView = (TextView) findViewById(R.id.video_textView);
		mProgressBar = (ProgressBar) findViewById(R.id.video_progressBar);

        mVideoView.setVideoPath(mVideo.getPath());

        MediaController controller = new MediaController(this);
        controller.setAnchorView(mVideoView);
        mVideoView.setMediaController(controller);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });
	}
}