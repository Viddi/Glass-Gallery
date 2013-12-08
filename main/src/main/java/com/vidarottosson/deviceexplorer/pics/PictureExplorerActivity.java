package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureExplorerActivity extends Activity {

	public static final String TAG = PictureExplorerActivity.class.getSimpleName();

	private CardScrollView mView;
	private PicturesScrollAdapter mAdapter;

    public static final String EXTENSION_PNG = ".png";
    public static final String EXTENSION_JPG = ".jpg";
    public static final String EXTENSION_JPEG = ".jpeg";
    public static final String EXTENSION_BMP = ".bmp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new PicturesScrollAdapter(this, queryImages());
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

	private List<String> queryImages() {
		List<String> pathList = new ArrayList<String>();

		File f = new File("/mnt/sdcard/DCIM/Camera/");
		File[] files = f.listFiles();

        Log.i(TAG, "Querying images..");

		for (int i = 0; i < files.length; i++) {
			File file = files[i];

            if(isImage(file.getName())) {
                pathList.add(file.getAbsolutePath());
                Log.i(TAG, "Added an image: " + file.getAbsolutePath());
            }

		}

        Log.i(TAG, "Done querying images..");

		return pathList;
	}

    private boolean isImage(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.'));

        if(extension.equals(EXTENSION_JPG) || extension.equals(EXTENSION_PNG) || extension.equals(EXTENSION_JPEG) || extension.equals(EXTENSION_BMP)) {
            return true;
        }

        return false;
    }

}
