package com.vidarottosson.deviceexplorer.pics;

//  Created by Viddi on 12/6/13.

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

public class PictureExplorerActivity extends Activity {

	public static final String TAG = PictureExplorerActivity.class.getSimpleName();

	private CardScrollView mView;
	private PicturesScrollAdapter mAdapter;
	private Cursor mCursor;
	private int mColumnIndex;
	private List<Uri> mUris;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		queryGallery();
		mAdapter = new PicturesScrollAdapter(this, mUris);
		mView = new CardScrollView(this);
		mView.setAdapter(mAdapter);

		setContentView(mView);
	}

	public void queryGallery() {
		String[] projection = {MediaStore.Images.Thumbnails._ID};
		mCursor = getContentResolver().query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.Thumbnails.IMAGE_ID);
		mColumnIndex = mCursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
		mUris = new ArrayList<Uri>(mCursor.getCount());

		mCursor.moveToFirst();

		while (mCursor.moveToNext()) {
			int imageID = mCursor.getInt(mColumnIndex);
			mUris.add(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));

            Log.i(TAG, "Added URI");
        }
	}
}
