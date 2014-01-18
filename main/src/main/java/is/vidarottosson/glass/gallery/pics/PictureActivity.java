package is.vidarottosson.glass.gallery.pics;

//  Created by Viddi on 12/6/13.

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
import is.vidarottosson.glass.gallery.models.PictureItem;
import is.vidarottosson.glass.gallery.util.Utility;

public class PictureActivity extends Activity {

	public static final String TAG = PictureActivity.class.getSimpleName();

	private CardScrollView mView;
	private PictureScrollAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new PictureScrollAdapter(this, queryImages());
		mView = new CardScrollView(this);
		mView.setAdapter(mAdapter);

		setContentView(mView);
	}

	@Override
	public void onResume() {
		super.onResume();
		mView.activate();
	}

	@Override
	public void onPause() {
		super.onPause();
		mView.deactivate();
	}

	private List<PictureItem> queryImages() {
		List<PictureItem> pathList = new ArrayList<PictureItem>();

		File f = new File(Utility.MEDIA_FOLDER_SOURCE);
		File[] files = f.listFiles();

		Log.i(TAG, "Querying images..");

		/* Sorting the files */
		Arrays.sort(files, new Comparator() {
			public int compare(Object o1, Object o2) {

				if (((File) o1).lastModified() > ((File) o2).lastModified()) {
					return -1;
				} else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
					return +1;
				} else {
					return 0;
				}
			}
		});

		for (File file : files) {

			if (Utility.isImage(file.getName())) {
				PictureItem item = new PictureItem(file.getAbsolutePath(), file.getName());
				item.setFileType(FileItem.Type.PICTURE);

				pathList.add(item);
				Log.i(TAG, "Added an image: " + file.getAbsolutePath());
			}

		}

		Log.i(TAG, "Done querying images..");

		return pathList;
	}
}