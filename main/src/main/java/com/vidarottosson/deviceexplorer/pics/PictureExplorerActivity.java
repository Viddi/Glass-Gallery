package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.app.Activity;
import android.os.Bundle;

import com.google.android.glass.widget.CardScrollView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureExplorerActivity extends Activity {

	public static final String TAG = PictureExplorerActivity.class.getSimpleName();

	private CardScrollView mView;
	private PicturesScrollAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAdapter = new PicturesScrollAdapter(this, queryImages());
		mView = new CardScrollView(this);
		mView.setAdapter(mAdapter);

		setContentView(mView);
	}

	private List<String> queryImages() {
		List<String> pathList = new ArrayList<String>();

		File f = new File("/DCIM/camera/");
		File[] files = f.listFiles();

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			pathList.add(file.getPath());
		}

		return pathList;
	}

}
