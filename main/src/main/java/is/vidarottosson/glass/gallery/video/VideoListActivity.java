package is.vidarottosson.glass.gallery.video;

//  Created by jonathon on 1/29/14.

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;
import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class VideoListActivity extends Activity implements GestureDetector.BaseListener {
	public static final String TAG = VideoListActivity.class.getSimpleName();

	private CardScrollView mView;
	private VideoScrollAdapter mAdapter;
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

		mAdapter = new VideoScrollAdapter(this, queryVideos());
		mView = new CardScrollView(this);
		mView.setAdapter(mAdapter);

		setContentView(mView);
		mDetector = new GestureDetector(this).setBaseListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mView.activate();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mView.deactivate();
	}

	//     ____       _            _         __  __      _   _               _
	//    |  _ \ _ __(_)_   ____ _| |_ ___  |  \/  | ___| |_| |__   ___   __| |___
	//    | |_) | '__| \ \ / / _` | __/ _ \ | |\/| |/ _ \ __| '_ \ / _ \ / _` / __|
	//    |  __/| |  | |\ V / (_| | ||  __/ | |  | |  __/ |_| | | | (_) | (_| \__ \
	//    |_|   |_|  |_| \_/ \__,_|\__\___| |_|  |_|\___|\__|_| |_|\___/ \__,_|___/

	private List<VideoItem> queryVideos() {
		List<VideoItem> pathList = new ArrayList<VideoItem>();

		File f = new File(Utility.MEDIA_FOLDER_SOURCE);
		File[] files = f.listFiles();

		Log.i(TAG, "Querying videos...");

        if (files == null) {
            // TODO: show the user that there are no files
        }

		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				if (f1.lastModified() > f2.lastModified()) {
					return -1;
				} else if (f1.lastModified() < f2.lastModified()) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		for (File file : files) {
			if (Utility.isVideo(file.getName())) {
				VideoItem item = new VideoItem(file.getAbsolutePath(), file.getName());

				pathList.add(item);
				Log.i(TAG, "Added a video: " + file.getAbsoluteFile());
			}
		}

		Log.i(TAG, "Finished querying images.");

		return pathList;
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		return mDetector.onMotionEvent(event);
	}

	@Override
	public boolean onGesture(Gesture gesture) {
        Log.i(TAG, "the gesture is: " + gesture);
		if (gesture == Gesture.TAP || gesture == Gesture.LONG_PRESS) {
			int position = mView.getSelectedItemPosition();
			VideoItem video = mAdapter.getItem(position);

			Intent intent = new Intent(this, VideoActivity.class);
			intent.putExtra(VideoItem.KEY_FOR_INTENT_EXTRA, video);
			startActivity(intent);
		}

		return false;
	}
}
