package is.vidarottosson.glass.gallery.video;

//  Created by jonstaff on 1/17/14.

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import is.vidarottosson.glass.gallery.models.FileItem;
import is.vidarottosson.glass.gallery.models.VideoItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class VideoActivity extends Activity {
	public static final String TAG = VideoActivity.class.getSimpleName();

	private CardScrollView mView;
	private VideoScrollAdapter mAdapter;

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
				item.setFileType(FileItem.Type.VIDEO);

				pathList.add(item);
				Log.i(TAG, "Added a video: " + file.getAbsoluteFile());
			}
		}

		Log.i(TAG, "Finished querying images.");

		return pathList;
	}
}
